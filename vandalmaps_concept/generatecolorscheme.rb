colormappings = { "g"=>"grey","r"=>"red","b"=>"blue","y"=>"yellow", 
			    "p" => "purple", "o" => "orange", "go" => "gold", "gr" => "green" }

class Awesome
	attr_accessor :prefix, :colorscheme
end

File.open("color_database.txt") do |f|
	@lines = f.readlines
end

x=1

puts "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
puts "<resources>"
@lines.each do |line|
	x=1 if x==6
		
	prefix, colorscheme = line.split(',')
	puts "\t<color name=\"#{colormappings[prefix]}#{x}\">\##{colorscheme.strip}</color>"	
	x+=1
end
puts "</resources>"