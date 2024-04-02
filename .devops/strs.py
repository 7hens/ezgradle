import re

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
