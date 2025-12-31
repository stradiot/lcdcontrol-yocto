SUMMARY = "HD44780 LCD Kernel Driver"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

inherit module

SRC_URI = "https://github.com/stradiot/lcdcontrol/archive/refs/tags/v${PV}.tar.gz"

SRC_URI[sha256sum] = "a9ea8b73fea4724571c01d7833df0184996431c95b181bacb5db1fe2744d99c5"

S = "${WORKDIR}/lcdcontrol-${PV}"
