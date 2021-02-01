SUMMARY = "adds access credentials"
DESCRIPTION = "adds access credentials"
PR = "r1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://authorized_keys.d/id_rsa-shoe.pub"

S = "${WORKDIR}"

# PACKAGES =+ "${PN}-<subname>"

EXCLUDE_FROM_WORLD = "1"

do_install () {
    local target_dir="${D}/home/root/.ssh"
    mkdir -p  "${target_dir}"
    for i in $(ls "${WORKDIR}/authorized_keys.d"); do
        cat "${WORKDIR}/authorized_keys.d/${i}" >> "${target_dir}/authorized_keys"
    done
    chmod 0600 "${target_dir}/authorized_keys"
}

FILES_${PN} = "*"

# Prevents do_package failures with:
# debugsources.list: No such file or directory:
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
