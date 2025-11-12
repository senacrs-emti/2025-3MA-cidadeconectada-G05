import java.util.*;

public class ConsumoService {

    public static class Item {
        public String categoria;
        public String nome;
        public double potenciaW;  // W
        public double horasDia;   // h/dia
        public double diasMes;    // dias/mês
        public int quantidade;

        public Item(String categoria, String nome, double potenciaW, double horasDia, double diasMes, int quantidade) {
            this.categoria = categoria;
            this.nome = nome;
            this.potenciaW = potenciaW;
            this.horasDia = horasDia;
            this.diasMes = diasMes;
            this.quantidade = quantidade;
        }

        public double kwhMes() {
            return (potenciaW * horasDia * diasMes * quantidade) / 1000.0;
        }

        public double custoMes(double tarifa) {
            return kwhMes() * tarifa;
        }
    }

    public static class Resultado {
        public List<Item> itens = new ArrayList<>();
        public double totalKwh;
        public double totalCusto;

        public Resultado(List<Item> itens, double tarifa) {
            this.itens = itens;
            this.totalKwh = itens.stream().mapToDouble(Item::kwhMes).sum();
            this.totalCusto = itens.stream().mapToDouble(i -> i.custoMes(tarifa)).sum();
        }
    }

    public Resultado calcular(List<Item> itens, double tarifa) {
        return new Resultado(itens, tarifa);
    }

    // Catálogo básico (pode virar JSON/DB)
    public static List<Item> catalogoPadrao() {
        return Arrays.asList(
            new Item("Refrigeração","Geladeira (frost-free moderna)",70,24,30,1),
            new Item("Refrigeração","Freezer horizontal",90,24,30,1),
            new Item("Climatização","Ar-condicionado 9k BTU (split)",900,6,30,1),
            new Item("Climatização","Ar-condicionado 12k BTU (split)",1200,6,30,1),
            new Item("Climatização","Ventilador de mesa",60,6,30,1),
            new Item("Climatização","Aquecedor elétrico",1500,2,30,1),
            new Item("Cozinha","Micro-ondas",1200,0.3,30,1),
            new Item("Cozinha","Forno elétrico",1500,0.5,30,1),
            new Item("Cozinha","Air fryer",1400,0.4,30,1),
            new Item("Cozinha","Cooktop elétrico (boca)",1500,0.6,30,1),
            new Item("Cozinha","Purificador/Gelágua",100,4,30,1),
            new Item("Cozinha","Liquidificador",300,0.05,30,1),
            new Item("Cozinha","Batedeira",400,0.05,30,1),
            new Item("Cozinha","Cafeteira elétrica",800,0.2,30,1),
            new Item("Cozinha","Sanduicheira/Grill",750,0.2,30,1),
            new Item("Lavanderia","Máquina de lavar (médio)",500,0.5,30,1),
            new Item("Lavanderia","Secadora de roupas",2000,0.3,30,1),
            new Item("Limpeza","Aspirador de pó",1000,0.2,30,1),
            new Item("Passadoria","Ferro de passar",1000,0.3,30,1),
            new Item("Banho","Chuveiro elétrico (comum)",5500,0.33,30,1),
            new Item("Banho","Chuveiro elétrico (alta potência)",7500,0.33,30,1),
            new Item("Entretenimento","TV LED 32–43\"",60,4,30,1),
            new Item("Entretenimento","TV 4K 50–65\"",120,4,30,1),
            new Item("Entretenimento","Videogame (PS/Xbox)",120,1,30,1),
            new Item("Informática","Notebook (uso)",60,6,30,1),
            new Item("Informática","Desktop + monitor",200,6,30,1),
            new Item("Conectividade","Roteador Wi-Fi",10,24,30,1),
            new Item("Escritório","Impressora jato de tinta",40,0.05,30,1),
            new Item("Iluminação","Lâmpada LED 9W",9,4,30,1),
            new Item("Iluminação","Lâmpada LED 12W",12,4,30,1),
            new Item("Diversos","Carregador de celular",10,2,30,1),
            new Item("Diversos","Bomba d’água",750,0.5,30,1),
            new Item("Aquário","Bomba/filtro",15,24,30,1),
            new Item("Aquário","Aquecedor de aquário",25,8,30,1),
            new Item("Cozinha","Lava-louças",1800,0.5,30,1)
        );
    }

    public static void main(String[] args) {
        ConsumoService svc = new ConsumoService();
        List<Item> itens = new ArrayList<>();
        itens.add(new Item("Refrigeração","Geladeira (frost-free moderna)",70,24,30,1));
        itens.add(new Item("Climatização","Ar-condicionado 9k BTU (split)",900,6,30,1));
        double tarifa = 0.95; // R$/kWh
        Resultado r = svc.calcular(itens, tarifa);
        System.out.printf("Total kWh/mês: %.2f%n", r.totalKwh);
        System.out.printf("Total R$/mês: %.2f%n", r.totalCusto);
    }
}
