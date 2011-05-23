class Build 
	attr_accessor :acronym, :name, :x, :y
end	

File.open("map_database.txt") do|f|
	@lines = f.readlines
end

x=1
@lines.each do |line|
	acro, title, left, right = line.split('-')
	#puts "<string name=\"#{acro.strip.downcase}_title\">#{title.strip}</string>"
	#puts "<string name=\"#{acro.strip.downcase}_summary\">#{acro.strip.downcase}</string>"
	#puts "<CheckBoxPreference"
  	#puts "android:key=\"#{acro.strip.downcase}\""
  	#puts "android:title=\"@string/#{acro.strip.downcase}_title\""
  	#puts "android:summary=\"@string/#{acro.strip.downcase}_summary\""
  	#puts "android:defaultValue=\"false\" />"

	#puts "public static boolean get#{acro.strip.upcase}(Context context){"
	#puts "	 return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(\"#{acro.strip.downcase}\", option#{x});"
	#puts "}"
	#puts "private static final boolean option#{x} = false;" 
	puts "if(List_Buildings.get#{acro.strip.upcase}(this)){"
	puts "	GeoPoint point = new GeoPoint(#{left.strip.gsub(".","")}-#{right.strip.gsub(".","")});"
	puts "  OverlayItem overlayitem = new OverlayItem(point, \"\",\"#{acro.strip.downcase}\");"
	puts "  itemizedoverlay.addOverlay(overlayitem);"
	puts "}"
	puts ""
	#puts "#{left.strip.gsub(".","")}-#{right.strip.gsub(".","")}"	
	
#	x+=1
end