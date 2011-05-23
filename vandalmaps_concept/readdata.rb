database = File.new("data.txt", "r")

database.each { |line| 
	Array complete = Array.new
	Array awesome = Array.new
	awesome = line.split("-")
	awesome.each {|segment|
		complete.push (segment.strip.split(","))
	}
	puts "\"INSERT INTO buildinglist (xcoord,ycoord,name,abbrv,displayed) VALUES (-#{complete.at(2).at(1).strip},#{complete.at(2).at(0).strip},\'#{complete.at(1).at(0).strip}\',\'#{complete.at(0).at(0).strip}\',0)\","
}