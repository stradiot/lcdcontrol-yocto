FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Remove the default inittab provided by busybox/initscripts
SRC_URI:remove = "file://inittab"

# Add your custom inittab
SRC_URI:append = " \
    file://inittab \
"
# This ensures your inittab is installed correctly
do_install:append() {
    install -m 0644 ${WORKDIR}/inittab ${D}${sysconfdir}/inittab
}
