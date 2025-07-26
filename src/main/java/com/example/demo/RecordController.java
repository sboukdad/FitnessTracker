package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * RecordController class handles user interactions with the fitness tracker.
 * Controller manages routes for displaying records, adding new entries, deleting
 * existing ones, and updating the page with the latest data
 */
@Controller
public class RecordController {
    private RecordRepository repo;

    /**
     * Create Record Controller.
     */
    public RecordController(RecordRepository repo) {
        this.repo = repo;
    }

    /**
     * index method handles requests to homepage. It gets all saved records
     * and displays them in the html file
     * @param model carries data from controller to HTML page
     *              so Thymeleaf can display it
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        List<Record> recordList = getAllRecords();

        double totalMiles = 0.0;
        for(Record r : recordList) {
            totalMiles += r.getMiles();
        }
        String totalMilesFormatted = String.format("%.1f", totalMiles);

        model.addAttribute("record_list", recordList);
        model.addAttribute("totalMiles", totalMilesFormatted);
        return "index";
    }

    /**
     * Add a new record based on user input - it saves the data to a db,
     * updates the list of records, recalculates the mileage, and refreshes
     * @param route track/path taken
     * @param miles total miles of record
     * @param date date of workout
     * @param model carries data from controller to HTML page
     *              so Thymeleaf can display it
     * @return updated records
     */
    @GetMapping("/add_record")
    public String addRecord(String route, String miles, String date, Model model) {
        if (route == null || route.length() ==0) {
            route = "--------";
        }
        if (miles == null || miles.length() == 0) {
            miles = "0.0";
        }

        double dMiles = Double.parseDouble(miles);
        Record record = new Record(route, dMiles, date);
        repo.save(record);

        List<Record> recordList = getAllRecords();

        double totalMiles = 0.0;
        for(Record r : recordList) {
            totalMiles += r.getMiles();
        }
        String totalMilesFormatted = String.format("%.1f", totalMiles);

        model.addAttribute("toast", "New record added:  " + route + ".");
        model.addAttribute("totalMiles", totalMilesFormatted);
        model.addAttribute("record_list", recordList);
        return "index";
    }

    /**
     * Remove a specific record by its ID, updates the list of remaining records,
     * recalculates the total mileage, and returns the updated view
     * @param id key
     * @param model carries data from controller to HTML page
     *              so Thymeleaf can display it
     * @return updated records
     */
    @GetMapping("/delete_record")
    public String deleteRecord(Integer id, Model model) {
        Record record = this.repo.findById(id);
        if (record != null) {
            model.addAttribute("toast", "Deleted record:  " + record.getRoute() + ".");
            this.repo.delete(record);
        } else {
            model.addAttribute("toast", "Could not delete ID: " + id + ".");
        }

        List<Record> recordList = getAllRecords();

        double totalMiles = 0.0;
        for(Record r : recordList) {
            totalMiles += r.getMiles();
        }
        String totalMilesFormatted = String.format("%.1f", totalMiles);


        model.addAttribute("record_list", recordList);
        model.addAttribute("totalMiles", totalMilesFormatted);
        return "index";
    }

    private List<Record> getAllRecords() {
        Iterable<Record> recordIter = repo.findAll();
        List<Record> recordList = new ArrayList<>();
        for (Record currentRecord : recordIter) {
            recordList.add(currentRecord);
        }
        return recordList;
    }
}