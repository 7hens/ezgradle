import toml
import sys

import utils

toml_path = "../gradle/libs.versions.toml"
bom_gradle_path = "../ezgradle-bom/build.gradle.kts"
libs_start = "//#LIBS_START"
libs_end = "//#LIBS_END"


def update_bom(input_file, output_file):
    ind = "    "
    toml_data = utils.read_file(input_file, toml.load)
    libs = toml_data.get("libraries", {}).keys()
    plugins = toml_data.get("plugins", {}).keys()
    bom_libs, non_bom_libs = utils.split_list(libs, lambda x: x.endswith("bom") or x.endswith("Bom"))
    lines = []
    for lib in bom_libs:
        lines.append(f"{ind}api(platform(libs.{utils.lib_accessor(lib)}))")
    lines.append(f"{ind}constraints {{")
    for lib in non_bom_libs:
        lines.append(f"{ind}{ind}api(libs.{utils.lib_accessor(lib)})")
    for lib in plugins:
        lines.append(f"{ind}{ind}api(libs.plugins.{utils.lib_accessor(lib)}.plugin())")
    lines.append(f"{ind}}}")
    content = "\n".join(lines)
    old_text = utils.read_file(output_file)
    new_text = utils.replace_region(old_text, content, libs_start, libs_end)
    utils.write_file(output_file, new_text)


def parse_version(lib, toml_data):
    if isinstance(lib, str):
        return lib.split(":")[-1]
    if "version" in lib:
        return lib["version"]
    if "version.ref" in lib:
        version_ref = lib["version_ref"]
        return toml_data["versions"][version_ref]

def resolve_dep_lib_key(lib):
    if isinstance(lib, str):
        group, name, _ = lib.split(":")
    elif "module" in lib:
        group, name = lib["module"].split(":")
    else:
        group = lib["group"]
        name = lib["name"]
    return f"{group}:{name}"

def resolve_dep_lib(lib, toml_data):
    if isinstance(lib, str):
        return lib
    version = parse_version(lib, toml_data)
    lib_key = resolve_dep_lib_key(lib)
    return f"{lib_key}:{version}"


def resolve_dep_lib_name(lib):
    lib_key = resolve_dep_lib_key(lib)
    group, name = lib_key.split(":")
    return f"{utils.camel_case(group)}_{utils.camel_case(name)}"

def resolve_plugin_lib_name(lib):
    return utils.camel_case(lib["id"])


def update_toml():
    toml_data = utils.read_file(toml_path, toml.load)
    versions = toml_data.get("versions", {})
    libs = toml_data.get("libraries", {})
    plugins = toml_data.get("plugins", {})
    new_libs = {resolve_dep_lib_name(v): v for _, v in libs.items()}
    new_plugins = {resolve_plugin_lib_name(v): v for _, v in plugins.items()}
    toml_data["libraries"] = new_libs
    toml_data["plugins"] = new_plugins
    print(toml_data)
    utils.write_toml(f"{toml_path}.toml", toml_data)


if __name__ == "__main__":
    cmd = sys.argv[1]
    args = sys.argv[2:]
    match cmd:
        case "update_bom":
            update_bom(toml_path, bom_gradle_path)
        case "update_toml":
            update_toml()
