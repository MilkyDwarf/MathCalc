from sympy.integrals.risch import NonElementaryIntegral

import sympy.parsing.sympy_parser

from sympy.parsing import *
import sympy as sym
from sympy.core import *
from sympy.core.expr import *
from sympy import *
import random
from sympy.core.numbers import *
from sympy.sets import *
from sympy.parsing.sympy_parser import *

transformations = (standard_transformations + (implicit_multiplication_application,))


a = Symbol('a', real=True)
b = Symbol('b', real=True)
c = Symbol('c', real=True)
d = Symbol('d', real=True)
e = Symbol('e', real=True)
f = Symbol('f', real=True)
g = Symbol('g', real=True)
h = Symbol('h', real=True)
i = Symbol('i', real=True)
j = Symbol('j', real=True)
k = Symbol('k', real=True)
l = Symbol('l', real=True)
m = Symbol('m', real=True)
n = Symbol('n', real=True)
o = Symbol('o', real=True)
p = Symbol('p', real=True)
q = Symbol('q', real=True)
r = Symbol('r', real=True)
s = Symbol('s', real=True)
t = Symbol('t', real=True)
u = Symbol('u', real=True)
v = Symbol('v', real=True)
w = Symbol('w', real=True)
x = Symbol('x', real=True)
y = Symbol('y', real=True)
z = Symbol('z', real=True)




def visualize(function):
    function = str(function.replace("^", "**"))
    if "sqrt" in function and (len(function) - 1) - function.find("r") < 3:
        return parse_expr("sqr", transformations=transformations,
                          evaluate=False)
    try:
        return parse_expr(function, transformations=transformations, evaluate=False)
    except:
        if len(function) != 0:
            try:
                if (function[-1] == "*" and function[-2] == "*"):
                    return parse_expr(function[:-2], evaluate=False)
                else:
                    return parse_expr(function[:-1], transformations=transformations,
                                      evaluate=False)
            except:
                return visualize(str(function[:-1]).replace("**", "^"))




        else:
            return ""
def visualizeEquation(function):
    function = str(function.replace("^", "**"))
    if "sqrt" in function and (len(function) - 1) - function.find("r") < 3:
        return parse_expr("sqr", transformations=transformations,
                          evaluate=False)
    try:
        return parse_expr(function, transformations=transformations, evaluate=False)
    except:
        if len(function) != 0:
            try:
                if (function[-1] == "*" and function[-2] == "*"):
                    return parse_expr(function[:-2], evaluate=False)
                else:
                    return parse_expr(function[:-1], transformations=transformations,
                                      evaluate=False)
            except:
                if ("=" in function and function.count("=") == 1):
                    try:
                        func1 = visualize(function.split("=")[0])
                        func2 = visualize(function.split("=")[1])
                        return Eq(func1, func2)
                    except: return visualize(str(function[:-1]).replace("**", "^"))

                return visualize(str(function[:-1]).replace("**", "^"))

def LatVis(function):
    function = str(function.replace("^", "**"))
    try:
        return latex(parse_expr(function, transformations=transformations, evaluate=False))
    except:
        if len(function) != 0:
            try:
                if (function[-1] == "*" and function[-2] == "*"):
                    return latex(parse_expr(function[:-2], transformations=transformations, evaluate=False))
                else:
                    return latex(parse_expr(function[:-1], transformations=transformations,
                                            evaluate=False))
            except:
                return LatVis(str(function[:-1]).replace("**", "^"))
        else:
            return ""


def intVis(function, variable, lowLimit=None, highLimit=None):
    function = visualize(function)

    return latex(Integral(function, (variable, lowLimit, highLimit)))


def indefIntegrate(function, variable):
    if ("e" in function):
        function = function.replace("e", "E")
    function = function.replace("tg", "tan")
    function = function.replace("cotan", "cot")
    function = function.replace("ctg", "cot")
    function = function.replace("arcsin", "asin")
    function = function.replace("arccos", "acos")
    function = function.replace("arctg", "atan")
    function = function.replace("arctan", "atan")
    function = function.replace("arcctg", "acot")
    function = function.replace("actg", "acot")
    function = function.replace("ln", "log")
    function = function.replace("arcsinh", "asinh")
    function = function.replace("arccosh", "acosh")
    function = function.replace("arctanh", "atanh")
    function = function.replace("cotanh", 'coth')
    function = function.replace("arccoth", 'acoth')
    function = function.replace("acotanh", 'acoth')
    function = function.replace("arccotanh", 'acoth')

    function = str(visualize(function))
    left = str(intVis(function, variable, lowLimit=None, highLimit=None))
    right = str(latex(integrate(function, (variable, None, None))))
    if (left != right):
        return str(intVis(function, variable, lowLimit=None, highLimit=None)) + latex(" = ") + str(
            latex(integrate(function, (variable, None, None)))) + latex("+") + latex("C")
    else:
        return "error"












def defIntegrate(function, variable, lowLimit, highLimit):
    if ("e" in function):
        function = function.replace("e", "E")
    function = function.replace("tg", "tan")
    function = function.replace("cotan", "cot")
    function = function.replace("ctg", "cot")
    function = function.replace("arcsin", "asin")
    function = function.replace("arccos", "acos")
    function = function.replace("arctg", "atan")
    function = function.replace("arctan", "atan")
    function = function.replace("arcctg", "acot")
    function = function.replace("actg", "acot")
    function = function.replace("ln", "log")
    function = function.replace("arcsinh", "asinh")
    function = function.replace("arccosh", "acosh")
    function = function.replace("arctanh", "atanh")
    function = function.replace("cotanh", 'coth')
    function = function.replace("arccoth", 'acoth')
    function = function.replace("acotanh", 'acoth')
    function = function.replace("arccotanh", 'acoth')

    function = str(visualize(function))
    left = str(intVis(function, variable, lowLimit, highLimit))
    right1 = str(latex(integrate(function, (variable, None, None))))
    right2 = str(latex(simplify(integrate(function, (variable, lowLimit, highLimit)))))
    palka = "\\bigg|_{" +  str(lowLimit) + "}^{" + str(highLimit) + "}"
    print ((palka))
    if (left != right1):
        return (left + latex(" = ") + right1 + palka + latex("=") + right2)
    else:
        return "error"






def setVis(n, variable):
    function = 1
    try:
        if (float(n) % 1 == 0 and float(n) > 0):
            return(str((latex(Derivative(function, visualize(variable),n  )))).replace(str(function), ""))
        else:
            return str((latex(Derivative(function, "x", 1)))).replace(str(function), "")
    except:
        return str((latex(Derivative(function, "x", 1)))).replace(str(function), "")


def setVisSolve(n, variable, function):
    try:
        function = function.replace("e", "E")
        function = function.replace("tg", "tan")
        function = function.replace("cotan", "cot")
        function = function.replace("ctg", "cot")
        function = function.replace("arcsin", "asin")
        function = function.replace("arccos", "acos")
        function = function.replace("arctg", "atan")
        function = function.replace("arctan", "atan")
        function = function.replace("arcctg", "acot")
        function = function.replace("actg", "acot")
        function = function.replace("ln", "log")
        function = function.replace("arcsinh", "asinh")
        function = function.replace("arccosh", "acosh")
        function = function.replace("arctanh", "atanh")
        function = function.replace("cotanh", 'coth')
        function = function.replace("arccoth", 'acoth')
        function = function.replace("acotanh", 'acoth')
        function = function.replace("arccotanh", 'acoth')
        if (function != ""):
            function = visualize(function)
            try:
                if (float(n) % 1 == 0 and float(n) > 0):
                    return (str((latex(Derivative(function, visualize(variable), n)))))
                else:
                    return str((latex(Derivative(function, "x", 1))))
            except:
                return str((latex(Derivative(function, "x", 1))))
        else:
            return setVis(n,variable)
    except:
        return setVis(n, variable)



def setVisSolveReally(n, variable, function):
    try:
        if (float(n)%1 == 0.0 and float(n) > 0.0):
            return (  setVisSolve(n, variable, function) + latex("=") + latex( simplify(diff(visualize(function), visualize(variable),n))))
        else:
            return "false"
    except:
        return "false"






def determinantVis2(
                a1, a2,
                b1, b2
                ):

                    a = Matrix(
                        [
                            [visualize(a1), visualize(a2)],
                            [visualize(b1), visualize(b2)]
                        ]
                    )
                    return (latex(Determinant(a)) + latex("=") + latex(det(a)))
def determinantVis3(
                    a1, a2, a3,
                    b1, b2, b3,
                    c1, c2, c3
                    ):
    a = Matrix(
        [
            [visualize(a1), visualize(a2), visualize(a3)],
            [visualize(b1), visualize(b2), visualize(b3)],
            [visualize(c1), visualize(c2), visualize(c3)]
        ]
    )
    return (latex(Determinant(a)) + latex("=") + latex(det(a)))
def determinantVis4(
                    a1, a2, a3, a4,
                    b1, b2, b3, b4,
                    c1, c2, c3, c4,
                    d1, d2, d3, d4
                    ):
    a = Matrix(
        [
            [visualize(a1), visualize(a2), visualize(a3), visualize(a4)],
            [visualize(b1), visualize(b2), visualize(b3), visualize(b4)],
            [visualize(c1), visualize(c2), visualize(c3), visualize(c4)],
            [visualize(d1), visualize(d2), visualize(d3), visualize(d4)]
        ]
    )
    return (latex(Determinant(a)) + latex("=") + latex(det(a)))

def determinantVis5(
                    a1, a2, a3, a4,a5,
                    b1, b2, b3, b4,b5,
                    c1, c2, c3, c4, c5,
                    d1, d2, d3, d4, d5,
                    e1, e2, e3, e4, e5
                    ):
    a = Matrix(
        [
            [visualize(a1), visualize(a2), visualize(a3), visualize(a4), visualize(a5)],
            [visualize(b1), visualize(b2), visualize(b3), visualize(b4), visualize(b5)],
            [visualize(c1), visualize(c2), visualize(c3), visualize(c4), visualize(c5)],
            [visualize(d1), visualize(d2), visualize(d3), visualize(d4), visualize(d5)],
            [visualize(e1), visualize(e2), visualize(e3), visualize(e4), visualize(e5)]
        ]
    )
    return (latex(Determinant(a)) + latex("=") + latex(det(a)))
def determinantVis6(
                    a1, a2, a3, a4,a5,a6,
                    b1, b2, b3, b4,b5,b6,
                    c1, c2, c3, c4, c5,c6,
                    d1, d2, d3, d4, d5,d6,
                    e1, e2, e3, e4, e5,e6,
                    f1, f2, f3, f4, f5, f6
                    ):
    a = Matrix(
        [
            [visualize(a1), visualize(a2), visualize(a3), visualize(a4), visualize(a5), visualize(a6)],
            [visualize(b1), visualize(b2), visualize(b3), visualize(b4), visualize(b5), visualize(b6)],
            [visualize(c1), visualize(c2), visualize(c3), visualize(c4), visualize(c5), visualize(c6)],
            [visualize(d1), visualize(d2), visualize(d3), visualize(d4), visualize(d5), visualize(d6)],
            [visualize(e1), visualize(e2), visualize(e3), visualize(e4), visualize(e5), visualize(e6)],
            [visualize(f1),visualize(f2),visualize(f3),visualize(f4),visualize(f5),visualize(f6)]
        ]
    )
    return (latex(Determinant(a)) + latex("=") + latex(det(a)))


def determinantVis7(
                    a1, a2, a3, a4,a5,a6,a7,
                    b1, b2, b3, b4,b5,b6,b7,
                    c1, c2, c3, c4, c5,c6,c7,
                    d1, d2, d3, d4, d5,d6,d7,
                    e1, e2, e3, e4, e5,e6,e7,
                    f1, f2, f3, f4, f5, f6, f7,
                    g1, g2, g3, g4, g5, g6, g7
                    ):
    a = Matrix(
        [
            [visualize(a1), visualize(a2), visualize(a3), visualize(a4), visualize(a5), visualize(a6), visualize(a7)],
            [visualize(b1), visualize(b2), visualize(b3), visualize(b4), visualize(b5), visualize(b6) , visualize(b7)],
            [visualize(c1), visualize(c2), visualize(c3), visualize(c4), visualize(c5), visualize(c6), visualize(c7)],
            [visualize(d1), visualize(d2), visualize(d3), visualize(d4), visualize(d5), visualize(d6), visualize(d7)],
            [visualize(e1), visualize(e2), visualize(e3), visualize(e4), visualize(e5), visualize(e6), visualize(e7)],
            [visualize(f1),visualize(f2),visualize(f3),visualize(f4),visualize(f5),visualize(f6), visualize(f7)],
            [visualize(g1), visualize(g2), visualize(g3), visualize(g4), visualize(g5), visualize(g6), visualize(g7)]
        ]
    )
    return (latex(Determinant(a)) + latex("=") + latex(det(a)))



# def solveEq(n,
#             eq1, eq2=None, eq3=None, eq4 = None,eq5 = None, eq6=None, eq7=None, eq8 = None ):
#
#
#




setF = FiniteSet(x, y)

print(Union({m, x, y, z}, {m, x, y, z}))

def checkSolves(n, eq1, eq2 = None, eq3 = None, eq4= None, eq5 = None, eq6 = None, eq7= None, eq8 = None, eq9 = None, eq10= None):
#try:
        funcs= [eq1, eq2, eq3, eq4, eq5, eq6, eq7, eq8, eq9, eq10]
        vars = list()
        eqs = list()
        varsForReturn = FiniteSet()
        for i in funcs:
            eqs.append( visualizeEquation(str(i)) )
        for i in range(0, n):
            vars.append(visualize(str(eqs[i])).free_symbols)
            print( str(i) + "  " + str(vars[i]))
            if (len(vars[i]) > 0):
                varsForReturn = Union(varsForReturn, vars[i])
            print(varsForReturn)
            print(eqs[0])
            if (len(latex(solve(eqs[:-(10 - n)], varsForReturn)     )) >5):
                return True
            else:
                return False

    #except: return("error")




def solverReal(n, eq1, eq2 = None, eq3 = None, eq4= None, eq5 = None, eq6 = None, eq7= None, eq8 = None, eq9 = None, eq10= None ):
     try:
        funcs= [eq1, eq2, eq3, eq4, eq5, eq6, eq7, eq8, eq9, eq10]
        vars = list()
        eqs = list()
        varsForReturn = FiniteSet()
        for i in funcs:
            eqs.append( visualizeEquation(str(i)) )
        for i in range(0, n):
            vars.append(visualize(str(eqs[i])).free_symbols)

            if (len(vars[i]) > 0):
                varsForReturn = Union(varsForReturn, vars[i])

            num = 0

            for i in solve(eqs[:-(10 - n)], varsForReturn):

                if (str(i).find("I") != -1 and str(i).find("i") != -1):
                    num += 1

        if (num!=0):
            return( latex(solve(eqs[:-(10 - n)], varsForReturn) [:-num]       ))
        else:
            return (latex(solve(eqs[:-(10 - n)], varsForReturn)))
     except:
         return latex("Нет действительных решений")

print(solverReal(2, "x^5 + xy^4 = y^10 + y^6", "x^6 + x^2 = 8y^3 + 2y"))




def solverVis(n, eq1, eq2 = None, eq3 = None, eq4= None, eq5 = None, eq6 = None, eq7= None, eq8 = None, eq9 = None, eq10= None ):
    funcs= [eq1, eq2, eq3, eq4, eq5, eq6, eq7, eq8, eq9, eq10]
    vars = list()
    eqs = list()
    varsForReturn = FiniteSet()
    s = "\\begin{cases}"
    m = ""
    m = ""
    for i in funcs:
        eqs.append( visualizeEquation(str(i)) )
    if (n!=1):
        for i in eqs:
            if (i!= None):
                s = s + latex(i) + "\\\\"
        return (s[:-2] + "\\end{cases}")
    else:
        return latex(eqs[0])



def solverAll(n, eq1, eq2 = None, eq3 = None, eq4= None, eq5 = None, eq6 = None, eq7= None, eq8 = None, eq9 = None, eq10= None ):
    #try:
        funcs= [eq1, eq2, eq3, eq4, eq5, eq6, eq7, eq8, eq9, eq10]
        vars = list()
        eqs = list()
        varsForReturn = FiniteSet()
        for i in funcs:
            eqs.append( visualizeEquation(str(i)) )
        for i in range(0, n):
            vars.append(visualize(str(eqs[i])).free_symbols)
            if (len(vars[i]) > 0):
                varsForReturn = Union(varsForReturn, vars[i])


        return( latex(solve(eqs[:-(10 - n)], varsForReturn)     ))
    #except: return("error")






def DoubleIntegrateVis(function, firstVar, secondVar, lowLimitF, highLimitF, lowLimitS, highLimitS):
    func = (visualize(function))
    return (latex(Integral(func, (firstVar, lowLimitS, highLimitS), (secondVar, lowLimitF, highLimitF))))

def DoubleIntegrate(function, firstVar, secondVar, lowLimitF, highLimitF, lowLimitS, highLimitS):
    func = (visualize(function))
    return (DoubleIntegrateVis(function, firstVar, secondVar, lowLimitF, highLimitF, lowLimitS, highLimitS) + latex("=") + latex(integrate(func, (firstVar, lowLimitS, highLimitS), (secondVar, lowLimitF, highLimitF))))




expression = x**2 + x*y + y

# Находим общий множитель выражения
common_factor = gcd_terms(expression)

# Делим каждое слагаемое на общий множитель и выносим его за скобку
factored_expression = expand(expression / common_factor)


# Выводим результат
print("Выражение после деления каждого слагаемого на общий множитель и выноса его за скобку: ", factored_expression)




a = [1, 2, 3, 4, 5]
for c in a:
    print(c)


for i in range(10,  ):
    print(i)