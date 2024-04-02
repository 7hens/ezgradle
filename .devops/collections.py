
def split_list(list, filter):
    filter_in = []
    filter_out = []
    for element in list:
        if filter(element):
            filter_in.append(element)
        else:
            filter_out.append(element)
    return filter_in, filter_out
