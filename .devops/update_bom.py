import toml
import strs
import files
import collections
import libs

input_path = "../gradle/libs.versions.toml"
output_path = "../ezgradle-bom/build.gradle.kts"
libs_start = "//#LIBS_START"
libs_end = "//#LIBS_END"


def update_bom_with_toml(input_file, output_file):
    ind = "    "
    toml_data = files.read(input_file, toml.load)
    libs = toml_data.get("libraries", {}).keys()
    plugins = toml_data.get("plugins", {}).keys()
    bom_libs, non_bom_libs = collections.split_list(libs, lambda x: x.endswith("bom"))
    lines = []
    for lib in bom_libs:
        lines.append(f"{ind}api(platform(libs.{libs.lib_accessor(lib)}))")
    lines.append(f"{ind}constraints {{")
    for lib in non_bom_libs:
        lines.append(f"{ind}{ind}api(libs.{libs.lib_accessor(lib)})")
    for lib in plugins:
        lines.append(f"{ind}{ind}api(libs.plugins.{libs.lib_accessor(lib)}.plugin())")
    lines.append(f"{ind}}}")
    content = "\n".join(lines)
    old_text = files.read(output_file)
    new_text = files.replace_region(old_text, content, libs_start, libs_end)
    files.write(output_file, new_text)


if __name__ == "__main__":
    update_bom_with_toml(input_path, output_path)
