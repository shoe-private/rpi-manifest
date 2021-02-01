FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://25-wired.network \
"

do_install_append () {
    local target_dir;
    target_dir="${D}/${sysconfdir}/systemd/network"
    install -d "${target_dir}"
    for i in $(cd ${WORKDIR}; ls *.network); do
        install -m 0644 "${WORKDIR}/$i" "${target_dir}/$i"
    done
}

FILES_${PN}_append += " ${sysconfdir}/systemd/network/* \
"
