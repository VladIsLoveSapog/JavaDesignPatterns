package pattern1_creation.create6_multiton.task;

public class MultitonMain {
    public static void main(String[] args) {
//        // Получение экспортёров через мультитон
//        DocumentExporter pdfExporter1 = DocumentExporterMultiton.getExporter(ExportFormat.PDF);
//        DocumentExporter pdfExporter2 = DocumentExporterMultiton.getExporter(ExportFormat.PDF);
//
//        DocumentExporter csvExporter1 = DocumentExporterMultiton.getExporter(ExportFormat.CSV);
//        DocumentExporter csvExporter2 = DocumentExporterMultiton.getExporter(ExportFormat.CSV);
//
//        DocumentExporter xmlExporter1 = DocumentExporterMultiton.getExporter(ExportFormat.XML);
//        DocumentExporter xmlExporter2 = DocumentExporterMultiton.getExporter(ExportFormat.XML);
//
//        // Проверка, что для каждого формата существует только один экземпляр
//        System.out.println("Проверка единственности экземпляров:");
//        System.out.println("pdfExporter1 == pdfExporter2: " + (pdfExporter1 == pdfExporter2));
//        System.out.println("csvExporter1 == csvExporter2: " + (csvExporter1 == csvExporter2));
//        System.out.println("xmlExporter1 == xmlExporter2: " + (xmlExporter1 == xmlExporter2));
//
//        System.out.println("\nЭкспорт документов:");
//
//        // Экспорт содержимого в разные форматы
//        System.out.println(pdfExporter1.export("Отчёт за март"));
//        System.out.println(csvExporter1.export("имя;возраст;город"));
//        System.out.println(xmlExporter1.export("<record>данные</record>"));
    }
}
