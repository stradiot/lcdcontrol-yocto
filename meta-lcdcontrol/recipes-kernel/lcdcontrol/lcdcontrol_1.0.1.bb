SUMMARY = "HD44780 LCD Kernel Driver"
LICENSE = "GPL-2.0-only"
# Use a generic license file or one inside your repo
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

inherit module

SRC_URI = "https://github.com/stradiot/lcdcontrol/archive/refs/tags/v${PV}.tar.gz"

SRC_URI[sha256sum] = "7acb3e8ca89951ec5cfb1953416142c69c59da512c54fc4628a04c2bbc87482f"

# BitBake clones git repos into a folder named 'git'
S = "${WORKDIR}/lcdcontrol-${PV}"
