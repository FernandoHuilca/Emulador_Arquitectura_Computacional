import CPU.CPU;
import CPU.Cache;
import MemoriaPrincipal.*;

import java.io.IOException;

import static MemoriaPrincipal.Instrucciones.*;

// Author: Fernando Huilca
public class Main {
    public static void main(String[] args) {
        //Configuración de memoria y caché Inicial
        int tamañoDeDirección = 6; //bits
        int tamañoDeBloque = 8; //bytes
        int tamañoDeLaCache = 32; //bytes
        //Creación de la memoria que ocuparemos: // La memoria principal necesita saber el tamaño y los bloques
        MemoriaPrincipal memoriaPrincipal = new MemoriaPrincipal(tamañoDeDirección, tamañoDeBloque, tamañoDeLaCache);
        //Creación de la Cache:
        Cache cache = new Cache(tamañoDeLaCache, memoriaPrincipal);
        //Creación del CPU
        CPU cpu = new CPU(cache);
        //Imprimimos a ver si se creó correctamente:
        System.out.println("Características de la memoria principal ____________________________________________________");
        System.out.println("Numero de datos: " + memoriaPrincipal.getNúmeroDeDatos());
        System.out.println("Numero de bloques: " + memoriaPrincipal.getNúmeroDeBloques());

        System.out.println("Caso 1 : Creación e instancia de cero en datos _____________________________________________");
        imprimirDatosDeMemoria(memoriaPrincipal.getDatos());

        System.out.println("Caso2:  Prueba de agregar datos individuales: ______________________________________________");
        memoriaPrincipal.agregarDato(0, 23);
        memoriaPrincipal.agregarDato(1, 44);
        memoriaPrincipal.agregarDato(2, 55);
        imprimirDatosDeMemoria(memoriaPrincipal.getDatos());

        System.out.println("Caso 3: Prueba de leer archivos y cargar en memoria: _______________________________________");
        try {
            memoriaPrincipal.cargarDatosDesdeArchivo("src/DatosEnMemoriaPrincipal.txt");
        } catch (IOException e) {
            System.err.println("ERROR, NO SE CARGÓ CORRECTAMENTE EL ARCHIVO: " + e.getMessage());
        }
        imprimirDatosDeMemoria(memoriaPrincipal.getDatos());

        System.out.println("Caso 4: Prueba de escribir en archivo datos de la memoria: _______________________________________");
        memoriaPrincipal.agregarDato(10, 69);
        memoriaPrincipal.agregarDato(11, 90);
        memoriaPrincipal.agregarDato(15, 90);
        try {
            memoriaPrincipal.guardarDatosEnArchivo("src/DatosEnMemoriaPrincipal.txt");
        } catch (IOException e) {
            System.err.println("ERROR, NO SE GUARDÓ CORRECTAMENTE EN EL ARCHIVO: " + e.getMessage());
        }
        imprimirDatosDeMemoria(memoriaPrincipal.getDatos());


        System.out.println("Caso 5: Operación ADD : _______________________________________");
        cpu.imprimirEnConsolaCPU();
        cpu.ejecutarInstrucción(ADD, 0, 1);
        cpu.imprimirEnConsolaCPU();

        System.out.println("Caso 6: Operación LOAD del cpu : _______________________________________");
        cpu.imprimirEnConsolaCPU();
        cache.imprimirCacheAtributos();
        cache.imprimirLíneas();
        System.out.println("____________________________________________________________________");
        cpu.ejecutarInstrucción(LOAD, 0, 15);
        cpu.imprimirEnConsolaCPU();
        cache.imprimirCacheAtributos();
        cache.imprimirLíneas();
        System.out.println("____________________________________________________________________");
        cpu.ejecutarInstrucción(LOAD, 1, 5);
        cpu.imprimirEnConsolaCPU();
        cache.imprimirCacheAtributos();
        cache.imprimirLíneas();
        System.out.println("____________________________________________________________________");
        cpu.ejecutarInstrucción(LOAD, 0, 0);
        cpu.imprimirEnConsolaCPU();
        cache.imprimirCacheAtributos();
        cache.imprimirLíneas();
        System.out.println("____________________________________________________________________");

        System.out.println("Caso 7: Prueba transformación binaria y decimal : _______________________________________");
        int valor = 2;
        System.out.println("El valor " + valor + " en binario es: " + SistemasNumericos.decimalABinarioDevuelveString(valor,8));
        int valor2 = 11111111;
        System.out.println("El valor " + valor2 + " en decimal es: " + SistemasNumericos.binarioADecimal(valor2));
        System.out.println("El valor " + valor + " en decimal String es: " + SistemasNumericos.decimalABinarioDevuelveString(valor, tamañoDeDirección));


        System.out.println("Caso 8: Operación STORE : _______________________________________");
        cpu.imprimirEnConsolaCPU();
        cache.imprimirCacheAtributos();
        cache.imprimirLíneas();
        cpu.ejecutarInstrucción(STORE, 1, 11);
        cpu.imprimirEnConsolaCPU();
        cache.imprimirCacheAtributos();
        cache.imprimirLíneas();
        try {
            memoriaPrincipal.guardarDatosEnArchivo("src/DatosEnMemoriaPrincipal.txt");
        } catch (IOException e) {
            System.err.println("ERROR, NO SE GUARDÓ CORRECTAMENTE EN EL ARCHIVO: " + e.getMessage());
        }
        imprimirDatosDeMemoria(memoriaPrincipal.getDatos());


    }


    private static void imprimirDatosDeMemoria(int[] datosAux) {
        for (int i = 0; i < datosAux.length; i++) {
            System.out.println("Dato número " + i + " " + datosAux[i]);
        }
    }
}