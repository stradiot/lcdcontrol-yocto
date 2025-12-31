require recipes-core/images/core-image-base.bb

DESCRIPTION = "lcdcontrol production image"

IMAGE_INSTALL:append = " \
    htop \
    curl \
    ssh-keys \
    rng-tools \
"

EXTRA_IMAGE_FEATURES += "ssh-server-openssh"
