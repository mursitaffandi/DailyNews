<?php
class DailyNews extends CI_Controller { 
	public function __construct () {
		parent::__construct();
	}

	public function AdapterAtrUpd()
	{
		$docAtrUpd = new DOMDocument();
		$objAtrUpd=array();
		$docAtrUpd->load('http://www.antara.co.id/rss/news.xml');
		$itemAtrUpd = $docAtrUpd->getElementsByTagName('item');
		foreach ($itemAtrUpd as $itemPros) {
			$titlesAtrUpd = $itemPros->getElementsByTagName('title');
			$titleAtrUpd = $titlesAtrUpd->item(0)->nodeValue;

			$linksAtrUpd = $itemPros->getElementsByTagName('link');
			$linkAtrUpd = $linksAtrUpd->item(0)->nodeValue;

			$datesAtrUpd = $itemPros->getElementsByTagName('pubDate');
			$dateAtrUpd = $datesAtrUpd->item(0)->nodeValue;

			$detailsAtrUpd = $itemPros->getElementsByTagName('description');
			$desAtrUpd = $detailsAtrUpd->item(0)->nodeValue;
			
			if (substr($desAtrUpd,1,3) == 'img') {
				$s = explode("\"",$desAtrUpd);
				$t = explode("\"",$s[1]);
			} else {
				$t[0] = base_url('/asset/image/logo_icon.png');
			}
			
			$data['source'] = 'Antara';
			$data['category'] = 'Terbaru';
			$data['title'] = $titleAtrUpd;
			$data['link'] = $linkAtrUpd;
			$data['pubDate'] = $dateAtrUpd;
			$data['image'] = $t[0];
			array_push($objAtrUpd,$data);
		}

		return $objAtrUpd;
	}

	public function AdapterDtkUpd()
	{	
		$docDtkSpt = new DOMDocument();
		$objDtkSpt=array();
		$docDtkSpt->load('http://rss.detik.com/index.php/sport');
		$itemDtkSpt = $docDtkSpt->getElementsByTagName('item');
		$i = 0;
		$objDtkSpt = array();
		foreach ($itemDtkSpt as $itemPros) {
			$titles = $itemPros->getElementsByTagName('title');
			$title = $titles->item(0)->nodeValue;

			$links = $itemPros->getElementsByTagName('link');
			$link = $links->item(0)->nodeValue;

			$dates = $itemPros->getElementsByTagName('pubDate');
			$date = $dates->item(0)->nodeValue;

			$details = $itemPros->getElementsByTagName('enclosure');
			$des = $details->item(0)->getAttribute('url');
			if ($des == null) {
				$des = base_url('/asset/image/logo_icon.png');
			}
			$data['source'] = 'Detik';
			$data['category'] = 'Olah Raga';
			$data['title'] = $title;
			$data['link'] = $link;
			$data['pubDate'] = $date;
			$data['image'] = $des;
			array_push($objDtkSpt,$data);
		}
		return $objDtkSpt;
	}

	public function apiDN() 
	{
		$data1 = $this->AdapterAtrUpd();
		$data2 = $this->AdapterDtkUpd();
		$ret['berita'] = array();
		foreach ($data1 as $data1) {
			array_push($ret['berita'],$data1);	
		}

		foreach ($data1 as $data1) {
			array_push($ret['berita'],$data2);
		}

		echo json_encode($ret);
	}
}
?>