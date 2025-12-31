SUMMARY = "Adds SSH public keys to root user"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://id_ed25519.pub"

S = "${WORKDIR}"

do_install() {
    install -d -m 0700 ${D}/root/.ssh
    install -m 0600 id_ed25519.pub ${D}/root/.ssh/authorized_keys
}

FILES:${PN} = "/root/.ssh/authorized_keys"
