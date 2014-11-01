# coding: utf-8

require 'mechanize'

agent = Mechanize.new

summary_page = agent.get('http://docs.oracle.com/javase/8/docs/api/overview-summary.html')

summary_page.root.css("td.colFirst > a").each do |link|
  package_page = Mechanize::Page::Link.new(link, agent, summary_page).click
  interface_table = package_page.root.css("table.typeSummary").find do |e|
    e.xpath("./caption/span")[0].text =~ /^Interface/
  end

  next if interface_table.nil?

  interface_table.css("td.colFirst").each do |e|
    next unless e.text =~ /Filter/

    if_link = e.css("a")[0]
    filter_page = Mechanize::Page::Link.new(if_link, agent, package_page).click

    method_table = filter_page.root.css("table.memberSummary").find do |e|
      e.attribute("summary").value =~ /^Method Summary/
    end
    method_count = method_table.css("td").size - 1
    puts "#{link.text}.#{e.text}" if method_count == 1
  end
end
