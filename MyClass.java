import java.util.Scanner; // Activa la antena para leer el teclado

// 1. CLASE BASE
class CuentaBancaria {
    String titular;
    double saldoDisponible;

    CuentaBancaria(String nombreTitular, double saldoInicial) {
        this.titular = nombreTitular;
        this.saldoDisponible = saldoInicial;
    }

    void registrarGasto(String concepto, double monto) {
        if (monto <= saldoDisponible) {
            saldoDisponible = saldoDisponible - monto;
            System.out.println("Gasto registrado: " + concepto + " por $" + monto);
        } else {
            System.out.println("Saldo insuficiente para: " + concepto);
        }
    }
} // FIN DE CODIGO ORIGINAL
// =========================================================================
// 2. CLASE DERIVADA
// =========================================================================
class CuentaEmergencia extends CuentaBancaria {
    double limitePermitido = 1000.0; // Atributo único: Tope máximo para compra

    CuentaEmergencia(String nombreTitular, double saldoInicial) {
        super(nombreTitular, saldoInicial); // REUTILIZACIÓN: Llama al constructor del padre
    }

    // SOBRESCRITURA: Método de bloqueo en gastos grandes
    @Override
    void registrarGasto(String concepto, double monto) {
        if (monto > limitePermitido) {
            System.out.println("¡¡¡Bloqueo de Emergencia!!!: '" + concepto + "' cuesta $" + monto + " y supera tu limite de $" + limitePermitido);
        } else {
            super.registrarGasto(concepto, monto); // Reusa la lógica del padre si el gasto lo permite
        }
    }
}

// 3. Simulación interactiva con Bucle "While"
public class MyClass {
    public static void main(String args[]) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("=== SISTEMA DE PRESUPUESTO CON HERENCIA ===");
        
        CuentaEmergencia cuenta = new CuentaEmergencia("Johana Lizeth", 5000.0);
        String articulo = "";

        // BUCLE: Repetir hasta salir o sin saldo
        while (!articulo.equalsIgnoreCase("salir") && cuenta.saldoDisponible > 0) {
            System.out.println("\n--- Saldo disponible: $" + cuenta.saldoDisponible + " | Limite por compra: $1000.0 ---");
            System.out.print("¿Que compraras? (o escribe 'salir'): ");
            articulo = teclado.nextLine();
            
            if (articulo.equalsIgnoreCase("salir")) {
                System.out.println("👋 Saliendo del sistema. ¡Excelente, cuidaste tu dinero!");
                break;
            }

            System.out.print("¿Cual es el costo? '" + articulo + "': $");
            double precio = teclado.nextDouble();
            teclado.nextLine(); // Limpieza del teclado

            cuenta.registrarGasto(articulo, precio); // Procesa el gasto usando la herencia
        }
        
        System.out.println("\n=== Sistema cerrado. Tu saldo final: $" + cuenta.saldoDisponible + " ===");
        teclado.close();
    }
}
