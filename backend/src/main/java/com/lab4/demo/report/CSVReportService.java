package com.lab4.demo.report;

import com.lab4.demo.movie.model.dto.MovieDTO;
import com.lab4.demo.movie.service.MovieService;
import com.lab4.demo.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.lab4.demo.report.ReportType.CSV;

@Service
@AllArgsConstructor
public class CSVReportService implements ReportService {

    private final MovieService movieService;
    private final OrderService orderService;

    @Override
    public File export() {
        List<MovieDTO> movies = new ArrayList<>();
        List<Long> top10ids = orderService.getTop10SoldIDs();

        for(Long id: top10ids){
            MovieDTO movieDTO = movieService.findById(id);
            movies.add(movieDTO);
        }

        File file = new File("top10movies.csv");

        try
        {
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.append("id");
            fileWriter.append(',');
            fileWriter.append("Name");
            fileWriter.append(',');
            fileWriter.append("Rating");
            fileWriter.append(',');
            fileWriter.append("No of Reviews");
            fileWriter.append(',');
            fileWriter.append("Price");
            fileWriter.append('\n');

            for(MovieDTO m: movies){
                fileWriter.append(String.valueOf(m.getId()));
                fileWriter.append(',');
                fileWriter.append(m.getName());
                fileWriter.append(',');
                fileWriter.append(String.valueOf(m.getRating()));
                fileWriter.append(',');
                fileWriter.append(String.valueOf(m.getNumber_reviews()));
                fileWriter.append(',');
                fileWriter.append(String.valueOf(m.getPrice()));
                fileWriter.append('\n');

            }
            fileWriter.flush();

        } catch (FileNotFoundException e) {
            System.out.println("Error writing the CSV file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public ReportType getType() {
        return CSV;
    }
}
