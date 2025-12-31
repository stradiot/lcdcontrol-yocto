#PACKAGECONFIG:append = " keyfile"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append = " file://NetworkManager.conf"
SRC_URI:append = " file://stradiot-dmz.nmconnection"

do_install:append() {
    install -m 0644 ${WORKDIR}/NetworkManager.conf ${D}${sysconfdir}/NetworkManager/NetworkManager.conf

    local NM_CONNECTIONS_DIR="${D}${sysconfdir}/NetworkManager/system-connections"
    install -d ${NM_CONNECTIONS_DIR}
    install -m 0600 ${WORKDIR}/stradiot-dmz.nmconnection ${NM_CONNECTIONS_DIR}
}

