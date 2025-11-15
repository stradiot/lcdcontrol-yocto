#!/bin/bash
cd /home/mstradiot/Documents/lcdcontrol-yocto/

source poky/oe-init-build-env ./build

# Remove the default, auto-generated files
rm conf/local.conf
rm conf/bblayers.conf

# Create symlinks to your version-controlled files
ln -s ../../yocto-conf/local.conf conf/local.conf
ln -s ../../yocto-conf/bblayers.conf conf/bblayers.conf
