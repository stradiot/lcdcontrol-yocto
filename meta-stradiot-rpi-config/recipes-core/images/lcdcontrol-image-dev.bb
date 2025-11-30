# 1. Define the Base: "Fat" Console Image
require recipes-extended/images/core-image-full-cmdline.bb

DESCRIPTION = "lcdcontrol development image"

# 3. Add Dev Tools (Neovim, GCC, Headers)
IMAGE_INSTALL:append = " \
    packagegroup-core-buildessential \
    kernel-dev \
    kernel-devsrc \
    htop \
    strace \
"

IMAGE_ROOTFS_EXTRA_SPACE = "2097152"
EXTRA_IMAGE_FEATURES += "debug-tweaks"
