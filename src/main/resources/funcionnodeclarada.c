int sumar(int a, int b);// declarado antes de main

int main() {
    calcular(5);        // error 1: todavia no esta declarada
    sumar(3, 2);        // sin error: tiene la funcion declarada antes de llamarla
    multiplicar(4, 2);  // error 2: no esta declarada ni definida

    return 0;
}

int calcular(int numero) {
    return numero;
}

//instrucciones que ejecutara
int sumar(int a, int b) {
    return a + b;
}