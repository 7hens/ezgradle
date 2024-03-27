import re
import os


def open_file(filename, mode, fn):
    file_path = os.path.join(os.path.dirname(__file__), filename)
    with open(file_path, mode) as file:
        return fn(file)


def read_file(filename, fn=lambda f: f.read()):
    return open_file(filename, "r", fn)


def write_file(filename, text):
    open_file(filename, "w", lambda f: f.write(text))


def replace_region(text, subtext, start, end):
    pattern = re.escape(start) + r"(.*?)" + re.escape(end)
    replacement = f"{start}\n{subtext}\n{end}"
    return re.sub(pattern, replacement, text, flags=re.DOTALL)


def split_list(list, filter):
    filter_in = []
    filter_out = []
    for element in list:
        if filter(element):
            filter_in.append(element)
        else:
            filter_out.append(element)
    return filter_in, filter_out
