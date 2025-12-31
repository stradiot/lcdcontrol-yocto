require recipes-extended/images/core-image-full-cmdline.bb

DESCRIPTION = "lcdcontrol development image"

IMAGE_INSTALL:append = " \
    packagegroup-core-buildessential \
    kernel-dev \
    kernel-devsrc \
    htop \
    strace \
    git \
    curl \
    vim \
    bison \
    flex \
    bc \
    openssl-dev \
    elfutils-dev \
    libgpiod-tools \
    rpi-gpio \
"

IMAGE_ROOTFS_EXTRA_SPACE = "2097152"
EXTRA_IMAGE_FEATURES += "debug-tweaks tools-sdk tools-debug"
