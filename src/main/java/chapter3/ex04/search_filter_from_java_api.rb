# coding: utf-8

require 'mechanize'

def search_filter(url)
  agent = Mechanize.new

  summary_page = agent.get(url)

  summary_page.root.css("td.colFirst > a").each do |link|
    package_page = Mechanize::Page::Link.new(link, agent, summary_page).click
    interface_table = package_page.root.css("table.typeSummary").find do |e|
      e.xpath("./caption/span")[0].text =~ /^Interface/
    end

    next if interface_table.nil?

    interface_table.css("td.colFirst").each do |e|
      if e.text =~ /Filter/
        if_link = e.css("a")[0]
        filter_page = Mechanize::Page::Link.new(if_link, agent, package_page).click
        method_table = filter_page.root.css("table.memberSummary").find do |elem|
          elem.attribute("summary").value =~ /^Method Summary/
        end
        method_count = method_table.css("td").size - 1
        puts "#{link.text}.#{e.text}" if method_count == 1
      end
    end
  end
end

[
  'http://docs.oracle.com/javase/8/docs/api/overview-summary.html',
  'http://docs.oracle.com/javaee/7/api/overview-summary.html',
  'http://docs.oracle.com/javafx/2/api/overview-summary.html',
].each do |url|
  search_filter url
end
