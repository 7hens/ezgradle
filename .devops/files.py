import os

def use(filename, mode, fn):
    file_path = os.path.join(os.path.dirname(__file__), filename)
    with open(file_path, mode) as file:
        return fn(file)


def read(filename, fn=lambda f: f.read()):
    return use(filename, "r", fn)


def write(filename, text):
    use(filename, "w", lambda f: f.write(text))
