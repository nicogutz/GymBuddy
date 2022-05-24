package be.kuleuven.gymbuddy.ui.main;

import java.util.ArrayList;
 
import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.SearchView;
 
public class NewActivity extends Activity implements
 SearchView.OnQueryTextListener, SearchView.OnCloseListener{
 
 private SearchView search;
 private MyListAdapter listAdapter;
 private ExpandableListView myList;
 private ArrayList<Continent> continentList = new ArrayList<Continent>();
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
  search = (SearchView) findViewById(R.id.search);
  search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
  search.setIconifiedByDefault(false);
  search.setOnQueryTextListener(this);
  search.setOnCloseListener(this);
   
  //display the list
  displayList();
  //expand all Groups
  expandAll();
 
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
     
    //method to expand all groups
 private void expandAll() {
  int count = listAdapter.getGroupCount();
  for (int i = 0; i < count; i++){
   myList.expandGroup(i);
  }
 }
  
 //method to expand all groups
 private void displayList() {
   
  //display the list
  loadSomeData();
   
  //get reference to the ExpandableListView
  myList = (ExpandableListView) findViewById(R.id.expandableList);
  //create the adapter by passing your ArrayList data
  listAdapter = new MyListAdapter(NewActivity.this, continentList);
  //attach the adapter to the list
  myList.setAdapter(listAdapter);
   
 }
  
 private void loadSomeData() {
   
  ArrayList<Country> countryList = new ArrayList<Country>();
  Country country = new Country("BMU","Bermuda",10000000);
  countryList.add(country);
  country = new Country("CAN","Canada",20000000);
  countryList.add(country);
  country = new Country("USA","United States",50000000);
  countryList.add(country);
   
  Continent continent = new Continent("North America",countryList);
  continentList.add(continent);
   
  countryList = new ArrayList<Country>();
  country = new Country("CHN","China",10000100);
  countryList.add(country);
  country = new Country("JPN","Japan",20000200);
  countryList.add(country);
  country = new Country("THA","Thailand",50000500);
  countryList.add(country);
   
  continent = new Continent("Asia",countryList);
  continentList.add(continent);
   
 }
 
 @Override
 public boolean onClose() {
  listAdapter.filterData("");
  expandAll();
  return false;
 }
 
 @Override
 public boolean onQueryTextChange(String query) {
  listAdapter.filterData(query);
  expandAll();
  return false;
 }
 
 @Override
 public boolean onQueryTextSubmit(String query) {
  listAdapter.filterData(query);
  expandAll();
  return false;
 }
}