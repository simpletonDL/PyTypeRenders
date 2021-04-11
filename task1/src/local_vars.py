import sys


def is_in_builtin(obj):
    return obj.__class__.__module__ == "builtins"


def get_vars(depth=0):
    depth += 1
    frame = sys._getframe(depth)
    return {
        var: is_in_builtin(value)
        for var, value in frame.f_locals.items()
    }


def print_vars():
    variables = get_vars(1)
    for var in sorted(variables):
        print(f'{var}: {(variables[var])}')
