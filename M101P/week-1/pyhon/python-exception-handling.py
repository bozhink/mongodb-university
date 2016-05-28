import sys

try:
    print(5 / 0)
except Exception as e:
    print('Exception: ', type(e), e)


print('But the life goes on')
