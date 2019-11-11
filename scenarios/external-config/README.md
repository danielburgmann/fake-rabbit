# External config file

This scenario shows the usage of an external config file.

Use the environment variable `FAKE_RABBIT_CONFIG` to set a UTF-8 encoded config properties file.

There is an example local config file in `scenarios/config-externalized/config.properties`.

To show that one can not only load local files but also from URLs there is an example made
at <https://pastebin.com/raw/UwmnP0Jk> . Set `FAKE_RABBIT_CONFIG` to
 `https://pastebin.com/raw/UwmnP0Jk` to see the result.
