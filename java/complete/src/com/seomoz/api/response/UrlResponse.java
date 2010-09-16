package com.seomoz.api.response;

/**
 * 
 * A Pojo to capture the json response from
 * URL Metrics
 * 
 * @author Radeep Solutions
 */
public class UrlResponse 
{
	/**
	 * The title of the page if available. For example: "Request-Response Format"
	 */
	private String ut;
	
	/**
	 * The url of the page.  For example: "apiwiki.seomoz.org/Request-Response+Format"
	 */
	private String uu;
	
	/**
	 * The subdomain of the url.  For example: "apiwiki.seomoz.org"
	 */
	private String ufq;
	
	/**
	 * The root domain of the url.  For example: "seomoz.org"
	 */
	private String upl;
	
	/**
	 * The number of juice-passing  external links to the url.
	 */
	private String ueid;
	
	/**
	 * The number of juice-passing external links to the subdomain of the url.
	 */
	private String feid;
	
	/**
	 * The number of juice-passing external links to the root domain of the url.
	 */
	private String peid;
	
	/**
	 * The number of juice-passing  links (internal or external) to the url.
	 */
	private String ujid;
	
	/**
	 * The number of subdomains with any pages linking to the url.
	 */
	private String uifq;
	
	/**
	 * The number of root domains with any pages linking to the url.
	 */
	private String uipl;
	
	/**
	 * The number of links (juice-passing  or not, internal or external) to the url.
	 */
	private String uid;
	
	/**
	 * The number of subdomains with any pages linking to the subdomain of the url.
	 */
	private String fid;
	
	/**
	 * The number of root domains with any pages linking to the root domain of the url.
	 */
	private String pid;
	
	/**
	 * The mozRank of the url.  Requesting this metric will provide both the pretty 10-point score (in umrp) and the raw score (umrr).
	 */
	private String umrp;
	
	/**
	 * The mozRank of the url.  Requesting this metric will provide both the pretty 10-point score (in umrp) and the raw score (umrr).
	 */
	private String umrr;
	
	/**
	 * The mozRank of the subdomain of the url.  Requesting this metric will provide both the pretty 10-point score (fmrp) and the raw score (fmrr).
	 */
	private String fmrp;

	/**
	 * The mozRank of the subdomain of the url.  Requesting this metric will provide both the pretty 10-point score (fmrp) and the raw score (fmrr).
	 */
	private String fmrr;

	/**
	 * The mozRank of the Root Domain of the url.  Requesting this metric will provide both the pretty 10-point score (pmrp) and the raw score (pmrr).
	 */
	private String pmrp;
	
	/**
	 * The mozRank of the Root Domain of the url.  Requesting this metric will provide both the pretty 10-point score (pmrp) and the raw score (pmrr).
	 */
	private String pmrr;
	
	/**
	 * The mozTrust of the url.    Requesting this metric will provide both the pretty 10-point score (utrp) and the raw score (utrr).
	 */
	private String utrp;
	
	/**
	 * The mozTrust of the url.    Requesting this metric will provide both the pretty 10-point score (utrp) and the raw score (utrr).
	 */
	private String utrr;
	
	/**
	 * The mozTrust of the subdomain of the url.  Requesting this metric will provide both the pretty 10-point score (ftrp) and the raw score (ftrr).
	 */
	private String ftrp;
	
	/**
	 * The mozTrust of the subdomain of the url.  Requesting this metric will provide both the pretty 10-point score (ftrp) and the raw score (ftrr).
	 */
	private String ftrr;
	
	/**
	 * The mozTrust of the root domain of the url.  Requesting this metric will provide both the pretty 10-point score (ptrp) and the raw score (ptrr).
	 */
	private String ptrp;
	
	/**
	 * The mozTrust of the root domain of the url.  Requesting this metric will provide both the pretty 10-point score (ptrp) and the raw score (ptrr).
	 */
	private String ptrr;
	
	/**
	 * he portion of the url's mozRank coming from external links.  Requesting this metric will provide both the pretty 10-point score (uemrp) and the raw score (uemrr).
	 */
	private String uemrp;

	/**
	 * he portion of the url's mozRank coming from external links.  Requesting this metric will provide both the pretty 10-point score (uemrp) and the raw score (uemrr).
	 */
	private String uemrr;

	/**
	 * The portion of the mozRank of all pages on the subdomain coming from external links.  Requesting this metric will provide both the pretty 10-point score (fejp) and the raw score (fejr).
	 */
	private String fejp;

	/**
	 * The portion of the mozRank of all pages on the subdomain coming from external links.  Requesting this metric will provide both the pretty 10-point score (fejp) and the raw score (fejr).
	 */
	private String fejr;

	/**
	 * The portion of the mozRank of all pages on the root domain coming from external links.  Requesting this metric will provide both the pretty 10-point score (pejp) and the raw score (pejr).
	 */
	private String pejp;

	/**
	 * The portion of the mozRank of all pages on the root domain coming from external links.  Requesting this metric will provide both the pretty 10-point score (pejp) and the raw score (pejr).
	 */
	private String pejr;

	/**
	 * The mozRank of all pages on the subdomain combined.  Requesting this metric will provide both the pretty 10-point score (fjp) and the raw score (fjr).
	 */
	private String fjp;

	/**
	 * The mozRank of all pages on the subdomain combined.  Requesting this metric will provide both the pretty 10-point score (fjp) and the raw score (fjr).
	 */
	private String fjr;

	/**
	 * The mozRank of all pages on the root domain combined.  Requesting this metric will provide both the pretty 10-point score (pjp) and the raw score (pjr).
	 */
	private String pjp;

	/**
	 * The mozRank of all pages on the root domain combined.  Requesting this metric will provide both the pretty 10-point score (pjp) and the raw score (pjr).
	 */
	private String pjr;

	/**
	 * If the url canaonicalizes to a different form, that canonical form will be available in this field.
	 */
	private String ur;
	
	/**
	 * The HTTP status code recorded by Linkscape for this URL (if available)
	 */
	private String us;
	
	/**
	 * Total links (including internal and nofollow links) to the subdomain of the url in question.
	 */
	private String fuid;
	
	/**
	 * Total links (including internal and nofollow links) to the root domain of the url in question.
	 */
	private String puid;
	
	/**
	 * The number of root domains with at least one link to the subdomain of the url in question.
	 */
	private String fipl;

	/**
	 * A score out of 100-points representing the likelihood for arbitrary content to rank on this page
	 */
	private String upa;
	
	/**
	 * A score out of 100-points representing the likelihood for arbitrary content to rank on this dom
	 */
	private String pda;
	
	/**
	 * @return the fmrp
	 */
	public String getFmrp() {
		return fmrp;
	}
	/**
	 * @param fmrp the fmrp to set
	 */
	public void setFmrp(String fmrp) {
		this.fmrp = fmrp;
	}
	/**
	 * @return the fmrr
	 */
	public String getFmrr() {
		return fmrr;
	}
	/**
	 * @param fmrr the fmrr to set
	 */
	public void setFmrr(String fmrr) {
		this.fmrr = fmrr;
	}
	/**
	 * @return the pda
	 */
	public String getPda() {
		return pda;
	}
	/**
	 * @param pda the pda to set
	 */
	public void setPda(String pda) {
		this.pda = pda;
	}
	/**
	 * @return the ueid
	 */
	public String getUeid() {
		return ueid;
	}
	/**
	 * @param ueid the ueid to set
	 */
	public void setUeid(String ueid) {
		this.ueid = ueid;
	}
	/**
	 * @return the ufq
	 */
	public String getUfq() {
		return ufq;
	}
	/**
	 * @param ufq the ufq to set
	 */
	public void setUfq(String ufq) {
		this.ufq = ufq;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the umrp
	 */
	public String getUmrp() {
		return umrp;
	}
	/**
	 * @param umrp the umrp to set
	 */
	public void setUmrp(String umrp) {
		this.umrp = umrp;
	}
	/**
	 * @return the umrr
	 */
	public String getUmrr() {
		return umrr;
	}
	/**
	 * @param umrr the umrr to set
	 */
	public void setUmrr(String umrr) {
		this.umrr = umrr;
	}
	/**
	 * @return the upa
	 */
	public String getUpa() {
		return upa;
	}
	/**
	 * @param upa the upa to set
	 */
	public void setUpa(String upa) {
		this.upa = upa;
	}
	/**
	 * @return the upl
	 */
	public String getUpl() {
		return upl;
	}
	/**
	 * @param upl the upl to set
	 */
	public void setUpl(String upl) {
		this.upl = upl;
	}
	/**
	 * @return the us
	 */
	public String getUs() {
		return us;
	}
	/**
	 * @param us the us to set
	 */
	public void setUs(String us) {
		this.us = us;
	}
	/**
	 * @return the ut
	 */
	public String getUt() {
		return ut;
	}
	/**
	 * @param ut the ut to set
	 */
	public void setUt(String ut) {
		this.ut = ut;
	}
	/**
	 * @return the uu
	 */
	public String getUu() {
		return uu;
	}
	/**
	 * @param uu the uu to set
	 */
	public void setUu(String uu) {
		this.uu = uu;
	}
	/**
	 * @return the feid
	 */
	public String getFeid() {
		return feid;
	}
	/**
	 * @param feid the feid to set
	 */
	public void setFeid(String feid) {
		this.feid = feid;
	}
	/**
	 * @return the peid
	 */
	public String getPeid() {
		return peid;
	}
	/**
	 * @param peid the peid to set
	 */
	public void setPeid(String peid) {
		this.peid = peid;
	}
	/**
	 * @return the ujid
	 */
	public String getUjid() {
		return ujid;
	}
	/**
	 * @param ujid the ujid to set
	 */
	public void setUjid(String ujid) {
		this.ujid = ujid;
	}
	/**
	 * @return the uifq
	 */
	public String getUifq() {
		return uifq;
	}
	/**
	 * @param uifq the uifq to set
	 */
	public void setUifq(String uifq) {
		this.uifq = uifq;
	}
	/**
	 * @return the uipl
	 */
	public String getUipl() {
		return uipl;
	}
	/**
	 * @param uipl the uipl to set
	 */
	public void setUipl(String uipl) {
		this.uipl = uipl;
	}
	/**
	 * @return the fid
	 */
	public String getFid() {
		return fid;
	}
	/**
	 * @param fid the fid to set
	 */
	public void setFid(String fid) {
		this.fid = fid;
	}
	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * @return the pmrp
	 */
	public String getPmrp() {
		return pmrp;
	}
	/**
	 * @param pmrp the pmrp to set
	 */
	public void setPmrp(String pmrp) {
		this.pmrp = pmrp;
	}
	/**
	 * @return the pmrr
	 */
	public String getPmrr() {
		return pmrr;
	}
	/**
	 * @param pmrr the pmrr to set
	 */
	public void setPmrr(String pmrr) {
		this.pmrr = pmrr;
	}
	/**
	 * @return the utrp
	 */
	public String getUtrp() {
		return utrp;
	}
	/**
	 * @param utrp the utrp to set
	 */
	public void setUtrp(String utrp) {
		this.utrp = utrp;
	}
	/**
	 * @return the utrr
	 */
	public String getUtrr() {
		return utrr;
	}
	/**
	 * @param utrr the utrr to set
	 */
	public void setUtrr(String utrr) {
		this.utrr = utrr;
	}
	/**
	 * @return the ftrp
	 */
	public String getFtrp() {
		return ftrp;
	}
	/**
	 * @param ftrp the ftrp to set
	 */
	public void setFtrp(String ftrp) {
		this.ftrp = ftrp;
	}
	/**
	 * @return the ftrr
	 */
	public String getFtrr() {
		return ftrr;
	}
	/**
	 * @param ftrr the ftrr to set
	 */
	public void setFtrr(String ftrr) {
		this.ftrr = ftrr;
	}
	/**
	 * @return the ptrp
	 */
	public String getPtrp() {
		return ptrp;
	}
	/**
	 * @param ptrp the ptrp to set
	 */
	public void setPtrp(String ptrp) {
		this.ptrp = ptrp;
	}
	/**
	 * @return the ptrr
	 */
	public String getPtrr() {
		return ptrr;
	}
	/**
	 * @param ptrr the ptrr to set
	 */
	public void setPtrr(String ptrr) {
		this.ptrr = ptrr;
	}
	/**
	 * @return the uemrp
	 */
	public String getUemrp() {
		return uemrp;
	}
	/**
	 * @param uemrp the uemrp to set
	 */
	public void setUemrp(String uemrp) {
		this.uemrp = uemrp;
	}
	/**
	 * @return the uemrr
	 */
	public String getUemrr() {
		return uemrr;
	}
	/**
	 * @param uemrr the uemrr to set
	 */
	public void setUemrr(String uemrr) {
		this.uemrr = uemrr;
	}
	/**
	 * @return the fejp
	 */
	public String getFejp() {
		return fejp;
	}
	/**
	 * @param fejp the fejp to set
	 */
	public void setFejp(String fejp) {
		this.fejp = fejp;
	}
	/**
	 * @return the fejr
	 */
	public String getFejr() {
		return fejr;
	}
	/**
	 * @param fejr the fejr to set
	 */
	public void setFejr(String fejr) {
		this.fejr = fejr;
	}
	/**
	 * @return the pejp
	 */
	public String getPejp() {
		return pejp;
	}
	/**
	 * @param pejp the pejp to set
	 */
	public void setPejp(String pejp) {
		this.pejp = pejp;
	}
	/**
	 * @return the pejr
	 */
	public String getPejr() {
		return pejr;
	}
	/**
	 * @param pejr the pejr to set
	 */
	public void setPejr(String pejr) {
		this.pejr = pejr;
	}
	/**
	 * @return the fjp
	 */
	public String getFjp() {
		return fjp;
	}
	/**
	 * @param fjp the fjp to set
	 */
	public void setFjp(String fjp) {
		this.fjp = fjp;
	}
	/**
	 * @return the fjr
	 */
	public String getFjr() {
		return fjr;
	}
	/**
	 * @param fjr the fjr to set
	 */
	public void setFjr(String fjr) {
		this.fjr = fjr;
	}
	/**
	 * @return the pjp
	 */
	public String getPjp() {
		return pjp;
	}
	/**
	 * @param pjp the pjp to set
	 */
	public void setPjp(String pjp) {
		this.pjp = pjp;
	}
	/**
	 * @return the pjr
	 */
	public String getPjr() {
		return pjr;
	}
	/**
	 * @param pjr the pjr to set
	 */
	public void setPjr(String pjr) {
		this.pjr = pjr;
	}
	/**
	 * @return the ur
	 */
	public String getUr() {
		return ur;
	}
	/**
	 * @param ur the ur to set
	 */
	public void setUr(String ur) {
		this.ur = ur;
	}
	/**
	 * @return the fuid
	 */
	public String getFuid() {
		return fuid;
	}
	/**
	 * @param fuid the fuid to set
	 */
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}
	/**
	 * @return the puid
	 */
	public String getPuid() {
		return puid;
	}
	/**
	 * @param puid the puid to set
	 */
	public void setPuid(String puid) {
		this.puid = puid;
	}
	/**
	 * @return the fipl
	 */
	public String getFipl() {
		return fipl;
	}
	/**
	 * @param fipl the fipl to set
	 */
	public void setFipl(String fipl) {
		this.fipl = fipl;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Response [feid=" + feid + ", fejp=" + fejp + ", fejr=" + fejr
				+ ", fid=" + fid + ", fipl=" + fipl + ", fjp=" + fjp + ", fjr="
				+ fjr + ", fmrp=" + fmrp + ", fmrr=" + fmrr + ", ftrp=" + ftrp
				+ ", ftrr=" + ftrr + ", fuid=" + fuid + ", pda=" + pda
				+ ", peid=" + peid + ", pejp=" + pejp + ", pejr=" + pejr
				+ ", pid=" + pid + ", pjp=" + pjp + ", pjr=" + pjr + ", pmrp="
				+ pmrp + ", pmrr=" + pmrr + ", ptrp=" + ptrp + ", ptrr=" + ptrr
				+ ", puid=" + puid + ", ueid=" + ueid + ", uemrp=" + uemrp
				+ ", uemrr=" + uemrr + ", ufq=" + ufq + ", uid=" + uid
				+ ", uifq=" + uifq + ", uipl=" + uipl + ", ujid=" + ujid
				+ ", umrp=" + umrp + ", umrr=" + umrr + ", upa=" + upa
				+ ", upl=" + upl + ", ur=" + ur + ", us=" + us + ", ut=" + ut
				+ ", utrp=" + utrp + ", utrr=" + utrr + ", uu=" + uu + "]";
	}
	
	
}
