# Frida error recreation

Detailing the steps taken and files used in my frida tests, and the output of frida fuzzer.

## Harness

First step was writing my harness to fit my android project *Login*.
The harness js file is *test_login.js*

The parts I changed from the example harness were "*ARG_TYPES*" and  the initializing of *func_handle*.

```
var ARG_TYPES = [uint32, uint32]

...

func_handle(payload_mem, payload_mem)

```

The arg types were changed to reflect pointers as the function being hooked unto takes in two strings as input.
The func_handle then takes in addresses of pointers for the same reason.

## CLI commands
the commands used to run frida fuzzer

First I compiled the script with the new harness

```
./node_modules/.bin/frida-compile tests/test_login.js -o fuzzer-agent.js
```

then with the app running on the emulator, I start frida-server using adb

```
adb shell su
cd data/local/tmp
./frida-server-x86
```

now on the host pc, I can start the frida-fuzzer.

```
./frida-fuzzre -U -o output_folder/1 com.example.login
```

## non default seed
I did try a few attemptw with a different seed than the default "0000", such as "a", "1234", "aaa" and a few other similar combinations.
The seed file is put into a folder "seed_folder".
for those attempts with non default seeds, I used a different command instead:

```
./frida-fuzzer -U -o output_folder/1 -i seed_folder/ com.example.login
```

## output
In all cases where the fuzzer runs properly, the output generated is a access-violation at a random address location.

![output](/frida_output.png)

#other details
the same crash output is return even if the android project is compiled wihout ASAN.


