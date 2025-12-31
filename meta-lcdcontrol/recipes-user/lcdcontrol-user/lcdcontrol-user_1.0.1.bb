SUMMARY = "LCD Control Userspace Tool"
DESCRIPTION = "Command line tool to control HD44780 LCD driver via read/write and ioctl"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "https://github.com/stradiot/lcdcontrol-user/archive/refs/tags/v${PV}.tar.gz"

SRC_URI[sha256sum] = "e90aa031b02fde7954f39cd8e0b9b535dc96693713e6b7e3d28839d8cd0c3b6a"

S = "${WORKDIR}/lcdcontrol-user-${PV}"

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/build/lcdtool ${D}${bindir}/lcdtool
}

TARGET_CC_ARCH += "${LDFLAGS}"
