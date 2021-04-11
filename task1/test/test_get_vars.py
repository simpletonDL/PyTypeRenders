import pandas as pd

from src.local_vars import print_vars


class MyClass:
    pass


def test_1(capsys):
    def foo():
        a = 1
        b = MyClass()
        c = [1, 2, 3]
        d = pd.DataFrame()
        print_vars()

    foo()

    expected = "\n".join([
        'a: True',
        'b: False',
        'c: True',
        'd: False',
        ''
    ])

    captured = capsys.readouterr()
    assert captured.out == expected


def test_2(capsys):
    def foo():
        def bar():
            def baz():
                z = (x, y)
                print_vars()
            y = 2
            baz()
        x = 1
        bar()

    foo()

    expected = "\n".join([
        'x: True',
        'y: True',
        'z: True',
        ''
    ])

    captured = capsys.readouterr()
    assert captured.out == expected


def test_3(capsys):
    def foo():
        x = 1
        y = MyClass()
        z = object()
        l = MyClass
        k = type
        m = "It is visible"
        print_vars()
        a = "It isn't visible"
        b = "It isn't visible too"

    foo()

    expected = "\n".join([
        'k: True',
        'l: True',
        'm: True',
        'x: True',
        'y: False',
        'z: True',
        ''
    ])

    captured = capsys.readouterr()
    assert captured.out == expected
