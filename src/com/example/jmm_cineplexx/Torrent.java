package com.example.jmm_cineplexx;

public class Torrent {
	private String fullTorrentName = "";
	private String torrentDownloadLink = "";
	private String torrentSize = "";
	private String seedsNumber = "";
	private String leechNumber = "";
	
	public Torrent(){
		
	}
	
	public Torrent(String fullTorrentName, String torrentDownloadLink,
			String torrentSize, String seed, String leech) {
		super();
		this.fullTorrentName = fullTorrentName;
		this.torrentDownloadLink = torrentDownloadLink;
		this.torrentSize = torrentSize;
		this.seedsNumber = seed;
		this.leechNumber = leech;
	}

	public String getFullTorrentName() {
		return fullTorrentName;
	}

	public void setFullTorrentName(String fullTorrentName) {
		this.fullTorrentName = fullTorrentName;
	}

	public String getTorrentDownloadLink() {
		return torrentDownloadLink;
	}

	public void setTorrentDownloadLink(String torrentDownloadLink) {
		this.torrentDownloadLink = torrentDownloadLink;
	}

	public String getTorrentSize() {
		return torrentSize;
	}

	public void setTorrentSize(String torrentSize) {
		this.torrentSize = torrentSize;
	}

	public String getSeedNumber() {
		return seedsNumber;
	}

	public void setSeedNumber(String seed) {
		this.seedsNumber = seed;
	}

	public String getLeechNumber() {
		return leechNumber;
	}

	public void setLeechNumber(String leech) {
		this.leechNumber = leech;
	}
	
	@Override
	public String toString() {
		return fullTorrentName + " - " + torrentSize + " - " + seedsNumber + " - " + leechNumber;
	}
	
}
