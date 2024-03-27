import toml
from common_utils import read_file, write_file, replace_region, split_list
from libs import lib_accessor

input_path = "../gradle/libs.versions.toml"
output_path = "../ezgradle-bom/build.gradle.kts"
libs_start = "//#LIBS_START"
libs_end = "//#LIBS_END"


def update_bom_with_toml(input_file, output_file):
    ind = "    "
    toml_data = read_file(input_file, toml.load)
    libs = toml_data.get("libraries", {}).keys()
    plugins = toml_data.get("plugins", {}).keys()
    bom_libs, non_bom_libs = split_list(libs, lambda x: x.endswith("bom"))
    lines = []
    for lib in bom_libs:
        lines.append(f"{ind}api(platform(libs.{lib_accessor(lib)}))")
    lines.append(f"{ind}constraints {{")
    for lib in non_bom_libs:
        lines.append(f"{ind}{ind}api(libs.{lib_accessor(lib)})")
    for lib in plugins:
        lines.append(f"{ind}{ind}api(libs.plugins.{lib_accessor(lib)}.plugin())")
    lines.append(f"{ind}}}")
    content = "\n".join(lines)
    old_text = read_file(output_file)
    new_text = replace_region(old_text, content, libs_start, libs_end)
    write_file(output_file, new_text)


if __name__ == "__main__":
    update_bom_with_toml(input_path, output_path)
