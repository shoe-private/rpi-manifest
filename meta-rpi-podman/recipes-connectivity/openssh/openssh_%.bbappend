FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://ssh.dnssd \
"
RDEPEND_${PN}_append += " systemd"

do_install_append () {
    local target_dir="${D}/${sysconfdir}/systemd/dnssd"
    target_dir="${D}/${sysconfdir}/systemd/dnssd"
    install -d "${target_dir}"
    for i in $(cd ${WORKDIR};ls *.dnssd); do
        install -m 0644 "${WORKDIR}/${i}" "${target_dir}/$i"
    done
}

FILES_${PN}-sshd += "${sysconfdir}/systemd/dnssd"