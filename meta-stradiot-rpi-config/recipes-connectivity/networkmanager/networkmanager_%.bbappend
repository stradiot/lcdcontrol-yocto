#PACKAGECONFIG:append = " keyfile"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append = " file://NetworkManager.conf"
do_install:append() {
    install -m 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/NetworkManager.conf
}

