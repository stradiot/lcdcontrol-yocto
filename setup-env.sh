#!/bin/bash
# Ensure you are in the project root (where .git and poky are)
# before sourcing oe-init-build-env.
cd /home/mstradiot/Documents/lcdcontrol-yocto/

# Source the oe-init-build-env script, telling it to use ./conf as the build directory.
source poky/oe-init-build-env ./build
