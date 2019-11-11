#!/usr/bin/env groovy

/**
 * Creates some load on the hello.txt endpoint.
 *
 * workloadThreads ... defines the number of parallel threads
 * numberOfRequests ... requests done by each thread
 * ports ... list of ports where apps are listening
 */

import static groovyx.gpars.GParsPool.withPool

def envWorkloadThreads = System.env['FAKE_RABBIT_SCENARIO_GLOWROOT_WORKLOAD_THREADS']
def envNumberOfRequests = System.env['FAKE_RABBIT_SCENARIO_GLOWROOT_NUMBER_OF_REQUESTS']
def envPorts = System.env['FAKE_RABBIT_SCENARIO_GLOWROOT_HELLO_APP_PORTS']
def envBaseUrl = System.env['FAKE_RABBIT_SCENARIO_GLOWROOT_HELLO_APP_BASE_URL']

// parameters
def workloadThreads = envWorkloadThreads?.isInteger() ? envWorkloadThreads.toInteger() : 12
def numberOfRequests = envNumberOfRequests?.isInteger() ? envNumberOfRequests.toInteger() : 100000
def ports = envPorts?.trim() ? envPorts.tokenize(',') : []
def baseUrl = envBaseUrl ?: 'http://127.0.0.1'

def parallelReqs = [numberOfRequests]*workloadThreads
def portCount = ports.size()
if(portCount < 1) {
    println "Could not identify ports in FAKE_RABBIT_SCENARIO_GLOWROOT_HELLO_APP_PORTS=$envPorts"
    exit(1)
}
withPool(workloadThreads) {

    parallelReqs.eachParallel { reqNo ->

        long threadId = Thread.currentThread().getId()

        println "# thread #$threadId"

        for(def i in 0..reqNo) {
            def port = ports[i % portCount]

            String requestUrl = "$baseUrl:$port/hello.txt?name=thread-$threadId-request-$i"

            boolean connectFailed = false
            String response
            try {
                response = requestUrl.toURL().text
            }
            catch (ConnectException e) {
                response = "${e.message} (Will wait one second before going on)"
                connectFailed = true
            }
            catch (NoRouteToHostException e) {
                response = "${e.message} (requestUrl: $requestUrl - Will wait one second before going on)"
                connectFailed = true
            }

            println "response #$i [threads:${workloadThreads},threadId:${threadId},port:${port}] : $response"
            if(connectFailed) {
                sleep(1000)
            }

        }
    }

}
