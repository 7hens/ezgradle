import os
import re
import toml

def replace_region(text, subtext, start, end):
    pattern = re.escape(start) + r"(.*?)" + re.escape(end)
    replacement = f"{start}\n{subtext}\n{end}"
    return re.sub(pattern, replacement, text, flags=re.DOTALL)

def pascal_case(text):
    words = re.split(r'[A-Za-z0-9]', text)
    return ''.join(word.capitalize() for word in words)

def camel_case(text):
    pascal_text = pascal_case(text)
    if not pascal_text:
        return ''
    return pascal_case[0].lower() + pascal_case[1:]

def split_list(list, filter):
    filter_in = []
    filter_out = []
    for element in list:
        if filter(element):
            filter_in.append(element)
        else:
            filter_out.append(element)
    return filter_in, filter_out


def use_file(filename, mode, fn):
    file_path = os.path.join(os.path.dirname(__file__), filename)
    with open(file_path, mode) as file:
        return fn(file)


def read_file(filename, fn=lambda f: f.read()):
    return use_file(filename, "r", fn)


def write_file(filename, text):
    use_file(filename, "w", lambda f: f.write(text))


def read_toml(file_path):
    return read_file(file_path, toml.load)


def parse_lib_module(lib_module, toml_data):
    if isinstance(lib_module, str):
        return lib_module
    elif isinstance(lib_module, dict):
        version = lib_module.get("version")
        if not version:
            version_ref = lib_module["version.ref"]
            version = toml_data["versions"][version_ref]
        module = lib_module.get("module")
        if not module:
            group = lib_module["group"]
            name = lib_module["name"]
            module = f"{group}:{name}"
        return f"{module}:{version}"
    else:
        raise ValueError(f"Unsupported lib_module format: {lib_module}")


def parse_plugin_module(plugin_module, toml_data):
    version = plugin_module.get("version")
    if not version:
        version_ref = plugin_module["version.ref"]
        version = toml_data["versions"][version_ref]
    id = plugin_module.get("id")
    return f"{id}:{version}"


def parse_libs(toml_data):
    versions = toml_data["versions"]
    libraries = toml_data.get("libraries", {})
    plugins = toml_data.get("plugins", {})
    result_libraries = {}
    result_plugins = {}
    for lib_name, lib_module in libraries.items():
        result_libraries[lib_name] = parse_lib_module(lib_module, toml_data)
    for plugin_name, plugin_module in plugins.items():
        result_plugins[plugin_name] = parse_plugin_module(plugin_module, toml_data)
    return {
        "versions": versions,
        "libraries": result_libraries,
        "plugins": result_libraries,
    }


def read_libs_from_toml(input_file):
    toml_data = read_toml(input_file)
    return parse_libs(toml_data)


def lib_accessor(lib_name):
    return lib_name.replace("-", ".")


def get_dependency_name(dependency):
    group, name, _ = dependency.split(":")
    return f"{camel_case(group)}-{camel_case(name)}"

