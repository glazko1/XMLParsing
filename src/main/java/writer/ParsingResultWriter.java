package writer;

import entity.CallPrices;
import entity.Parameters;
import entity.Tariff;
import entity.TariffingType;

import java.io.PrintWriter;
import java.util.List;

public class ParsingResultWriter {

    private static final ParsingResultWriter INSTANCE = new ParsingResultWriter();

    public static ParsingResultWriter getInstance() {
        return INSTANCE;
    }

    private ParsingResultWriter() {}

    public void writeResults(PrintWriter writer, List<Tariff> tariffs) {
        openTable(writer);
        writeHeader(writer);
        tariffs.forEach(tariff -> writeRow(writer, tariff));
        closeTable(writer);
    }

    private void openTable(PrintWriter writer) {
        writer.println("<table>");
    }

    private void writeHeader(PrintWriter writer) {
        writer.println("<tr>");
        writer.println("<th align=center>ID</th>");
        writer.println("<th align=center>Name</th>");
        writer.println("<th align=center>Operator Name</th>");
        writer.println("<th align=center>Payroll</th>");
        writer.println("<th align=center>Within network</th>");
        writer.println("<th align=center>Other networks</th>");
        writer.println("<th align=center>Landline Phones</th>");
        writer.println("<th align=center>SMS Price</th>");
        writer.println("<th align=center>Favorite Numbers</th>");
        writer.println("<th align=center>Tariffing</th>");
        writer.println("<th align=center>Connection Fee</th>");
        writer.println("</tr>");
    }

    private void writeRow(PrintWriter writer, Tariff tariff) {
        writer.println("<tr>");
        writer.println("<td align=center>" + tariff.getId() + "</td>");
        writer.println("<td align=center>" + tariff.getName() + "</td>");
        writer.println("<td align=center>" + tariff.getOperatorName() + "</td>");
        writer.println("<td align=center>" + tariff.getPayroll() + "</td>");
        CallPrices callPrices = tariff.getCallPrices();
        writer.println("<td align=center>" + callPrices.getWithinNetwork() + "</td>");
        writer.println("<td align=center>" + callPrices.getOtherNetworks() + "</td>");
        writer.println("<td align=center>" + callPrices.getLandlinePhones() + "</td>");
        writer.println("<td align=center>" + tariff.getSmsPrice() + "</td>");
        Parameters parameters = tariff.getParameters();
        writer.println("<td align=center>" + parameters.getFavoriteNumbers() + "</td>");
        TariffingType tariffingType = parameters.getTariffingType();
        String tariffing = tariffingType == TariffingType.SEC_60 ? "60-sec" : "12-sec";
        writer.println("<td align=center>" + tariffing + "</td>");
        writer.println("<td align=center>" + parameters.getConnectionFee() + "</td>");
        writer.println("</tr>");
    }

    private void closeTable(PrintWriter writer) {
        writer.println("</table>");
    }
}
