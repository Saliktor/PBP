package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.gamelogic.WorkingGame;

@Entity
@Table(name = "Game")
public class Game {
	
	@Id
	@SequenceGenerator(name="game_pk",sequenceName="game_seq", allocationSize=1)
	@GeneratedValue(generator="game_pk", strategy=GenerationType.SEQUENCE)
	int id;
	@JsonIgnore
	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
	Set<Move> moves = new HashSet<Move>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
	Set<Player> players = new HashSet<Player>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
	Set<Message> messages = new HashSet<Message>();
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "whose_turn")
	Team whoseTurn;

	int aa;
    int ab;
    int ac;
    int ad;
    int ae;
    int af;
    int ag;
    int ah;
    int ba;
    int bb;
    int bc;
    int bd;
    int be;
    int bf;
    int bg;
    int bh;
    int ca;
    int cb;
    int cc;
    int cd;
    int ce;
    int cf;
    int cg;
    int ch;
    int da;
    int db;
    int dc;
    int dd;
    int de;
    int df;
    int dg;
    int dh;
    int ea;
    int eb;
    int ec;
    int ed;
    int ee;
    int ef;
    int eg;
    int eh;
    int fa;
    int fb;
    int fc;
    int fd;
    int fe;
    int ff;
    int fg;
    int fh;
    int ga;
    int gb;
    int gc;
    int gd;
    int ge;
    int gf;
    int gg;
    int gh;
    int ha;
    int hb;
    int hc;
    int hd;
    int he;
    int hf;
    int hg;
    int hh;
	
	public Game() {
		super();
		this.dd = 1;
		this.de = 2;
		this.ed = 2;
		this.ee = 1;
	}
	
	public Game (WorkingGame g) {
	    this.id = g.id;
	    this.aa = g.boardstate[0][0].value;
	    this.ab = g.boardstate[0][1].value;
	    this.ac = g.boardstate[0][2].value;
	    this.ad = g.boardstate[0][3].value;
	    this.ae = g.boardstate[0][4].value;
	    this.af = g.boardstate[0][5].value;
	    this.ag = g.boardstate[0][6].value;
	    this.ah = g.boardstate[0][7].value;
	    this.ba = g.boardstate[1][0].value;
	    this.bb = g.boardstate[1][1].value;
	    this.bc = g.boardstate[1][2].value;
	    this.bd = g.boardstate[1][3].value;
	    this.be = g.boardstate[1][4].value;
	    this.bf = g.boardstate[1][5].value;
	    this.bg = g.boardstate[1][6].value;
	    this.bh = g.boardstate[1][7].value;
	    this.ca = g.boardstate[2][0].value;
	    this.cb = g.boardstate[2][1].value;
	    this.cc = g.boardstate[2][2].value;
	    this.cd = g.boardstate[2][3].value;
	    this.ce = g.boardstate[2][4].value;
	    this.cf = g.boardstate[2][5].value;
	    this.cg = g.boardstate[2][6].value;
	    this.ch = g.boardstate[2][7].value;
	    this.da = g.boardstate[3][0].value;
	    this.db = g.boardstate[3][1].value;
	    this.dc = g.boardstate[3][2].value;
	    this.dd = g.boardstate[3][3].value;
	    this.de = g.boardstate[3][4].value;
	    this.df = g.boardstate[3][5].value;
	    this.dg = g.boardstate[3][6].value;
	    this.dh = g.boardstate[3][7].value;
	    this.ea = g.boardstate[4][0].value;
	    this.eb = g.boardstate[4][1].value;
	    this.ec = g.boardstate[4][2].value;
	    this.ed = g.boardstate[4][3].value;
	    this.ee = g.boardstate[4][4].value;
	    this.ef = g.boardstate[4][5].value;
	    this.eg = g.boardstate[4][6].value;
	    this.eh = g.boardstate[4][7].value;
	    this.fa = g.boardstate[5][0].value;
	    this.fb = g.boardstate[5][1].value;
	    this.fc = g.boardstate[5][2].value;
	    this.fd = g.boardstate[5][3].value;
	    this.fe = g.boardstate[5][4].value;
	    this.ff = g.boardstate[5][5].value;
	    this.fg = g.boardstate[5][6].value;
	    this.fh = g.boardstate[5][7].value;
	    this.ga = g.boardstate[6][0].value;
	    this.gb = g.boardstate[6][1].value;
	    this.gc = g.boardstate[6][2].value;
	    this.gd = g.boardstate[6][3].value;
	    this.ge = g.boardstate[6][4].value;
	    this.gf = g.boardstate[6][5].value;
	    this.gg = g.boardstate[6][6].value;
	    this.gh = g.boardstate[6][7].value;
	    this.ha = g.boardstate[7][0].value;
	    this.hb = g.boardstate[7][1].value;
	    this.hc = g.boardstate[7][2].value;
	    this.hd = g.boardstate[7][3].value;
	    this.he = g.boardstate[7][4].value;
	    this.hf = g.boardstate[7][5].value;
	    this.hg = g.boardstate[7][6].value;
	    this.hh = g.boardstate[7][7].value;
	    
	    this.whoseTurn = g.whoseTurn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Move> getMoves() {
		return moves;
	}

	public void setMoves(Set<Move> moves) {
		this.moves = moves;
	}

	public int getAa() {
		return aa;
	}

	public void setAa(int aa) {
		this.aa = aa;
	}

	public int getAb() {
		return ab;
	}

	public void setAb(int ab) {
		this.ab = ab;
	}

	public int getAc() {
		return ac;
	}

	public void setAc(int ac) {
		this.ac = ac;
	}

	public int getAd() {
		return ad;
	}

	public void setAd(int ad) {
		this.ad = ad;
	}

	public int getAe() {
		return ae;
	}

	public void setAe(int ae) {
		this.ae = ae;
	}

	public int getAf() {
		return af;
	}

	public void setAf(int af) {
		this.af = af;
	}

	public int getAg() {
		return ag;
	}

	public void setAg(int ag) {
		this.ag = ag;
	}

	public int getAh() {
		return ah;
	}

	public void setAh(int ah) {
		this.ah = ah;
	}

	public int getBa() {
		return ba;
	}

	public void setBa(int ba) {
		this.ba = ba;
	}

	public int getBb() {
		return bb;
	}

	public void setBb(int bb) {
		this.bb = bb;
	}

	public int getBc() {
		return bc;
	}

	public void setBc(int bc) {
		this.bc = bc;
	}

	public int getBd() {
		return bd;
	}

	public void setBd(int bd) {
		this.bd = bd;
	}

	public int getBe() {
		return be;
	}

	public void setBe(int be) {
		this.be = be;
	}

	public int getBf() {
		return bf;
	}

	public void setBf(int bf) {
		this.bf = bf;
	}

	public int getBg() {
		return bg;
	}

	public void setBg(int bg) {
		this.bg = bg;
	}

	public int getBh() {
		return bh;
	}

	public void setBh(int bh) {
		this.bh = bh;
	}

	public int getCa() {
		return ca;
	}

	public void setCa(int ca) {
		this.ca = ca;
	}

	public int getCb() {
		return cb;
	}

	public void setCb(int cb) {
		this.cb = cb;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

	public int getCd() {
		return cd;
	}

	public void setCd(int cd) {
		this.cd = cd;
	}

	public int getCe() {
		return ce;
	}

	public void setCe(int ce) {
		this.ce = ce;
	}

	public int getCf() {
		return cf;
	}

	public void setCf(int cf) {
		this.cf = cf;
	}

	public int getCg() {
		return cg;
	}

	public void setCg(int cg) {
		this.cg = cg;
	}

	public int getCh() {
		return ch;
	}

	public void setCh(int ch) {
		this.ch = ch;
	}

	public int getDa() {
		return da;
	}

	public void setDa(int da) {
		this.da = da;
	}

	public int getDb() {
		return db;
	}

	public void setDb(int db) {
		this.db = db;
	}

	public int getDc() {
		return dc;
	}

	public void setDc(int dc) {
		this.dc = dc;
	}

	public int getDd() {
		return dd;
	}

	public void setDd(int dd) {
		this.dd = dd;
	}

	public int getDe() {
		return de;
	}

	public void setDe(int de) {
		this.de = de;
	}

	public int getDf() {
		return df;
	}

	public void setDf(int df) {
		this.df = df;
	}

	public int getDg() {
		return dg;
	}

	public void setDg(int dg) {
		this.dg = dg;
	}

	public int getDh() {
		return dh;
	}

	public void setDh(int dh) {
		this.dh = dh;
	}

	public int getEa() {
		return ea;
	}

	public void setEa(int ea) {
		this.ea = ea;
	}

	public int getEb() {
		return eb;
	}

	public void setEb(int eb) {
		this.eb = eb;
	}

	public int getEc() {
		return ec;
	}

	public void setEc(int ec) {
		this.ec = ec;
	}

	public int getEd() {
		return ed;
	}

	public void setEd(int ed) {
		this.ed = ed;
	}

	public int getEe() {
		return ee;
	}

	public void setEe(int ee) {
		this.ee = ee;
	}

	public int getEf() {
		return ef;
	}

	public void setEf(int ef) {
		this.ef = ef;
	}

	public int getEg() {
		return eg;
	}

	public void setEg(int eg) {
		this.eg = eg;
	}

	public int getEh() {
		return eh;
	}

	public void setEh(int eh) {
		this.eh = eh;
	}

	public int getFa() {
		return fa;
	}

	public void setFa(int fa) {
		this.fa = fa;
	}

	public int getFb() {
		return fb;
	}

	public void setFb(int fb) {
		this.fb = fb;
	}

	public int getFc() {
		return fc;
	}

	public void setFc(int fc) {
		this.fc = fc;
	}

	public int getFd() {
		return fd;
	}

	public void setFd(int fd) {
		this.fd = fd;
	}

	public int getFe() {
		return fe;
	}

	public void setFe(int fe) {
		this.fe = fe;
	}

	public int getFf() {
		return ff;
	}

	public void setFf(int ff) {
		this.ff = ff;
	}

	public int getFg() {
		return fg;
	}

	public void setFg(int fg) {
		this.fg = fg;
	}

	public int getFh() {
		return fh;
	}

	public void setFh(int fh) {
		this.fh = fh;
	}

	public int getGa() {
		return ga;
	}

	public void setGa(int ga) {
		this.ga = ga;
	}

	public int getGb() {
		return gb;
	}

	public void setGb(int gb) {
		this.gb = gb;
	}

	public int getGc() {
		return gc;
	}

	public void setGc(int gc) {
		this.gc = gc;
	}

	public int getGd() {
		return gd;
	}

	public void setGd(int gd) {
		this.gd = gd;
	}

	public int getGe() {
		return ge;
	}

	public void setGe(int ge) {
		this.ge = ge;
	}

	public int getGf() {
		return gf;
	}

	public void setGf(int gf) {
		this.gf = gf;
	}

	public int getGg() {
		return gg;
	}

	public void setGg(int gg) {
		this.gg = gg;
	}

	public int getGh() {
		return gh;
	}

	public void setGh(int gh) {
		this.gh = gh;
	}

	public int getHa() {
		return ha;
	}

	public void setHa(int ha) {
		this.ha = ha;
	}

	public int getHb() {
		return hb;
	}

	public void setHb(int hb) {
		this.hb = hb;
	}

	public int getHc() {
		return hc;
	}

	public void setHc(int hc) {
		this.hc = hc;
	}

	public int getHd() {
		return hd;
	}

	public void setHd(int hd) {
		this.hd = hd;
	}

	public int getHe() {
		return he;
	}

	public void setHe(int he) {
		this.he = he;
	}

	public int getHf() {
		return hf;
	}

	public void setHf(int hf) {
		this.hf = hf;
	}

	public int getHg() {
		return hg;
	}

	public void setHg(int hg) {
		this.hg = hg;
	}

	public int getHh() {
		return hh;
	}

	public void setHh(int hh) {
		this.hh = hh;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	
	public Team getWhoseTurn() {
		return whoseTurn;
	}

	public void setWhoseTurn(Team whoseTurn) {
		this.whoseTurn = whoseTurn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aa;
		result = prime * result + ab;
		result = prime * result + ac;
		result = prime * result + ad;
		result = prime * result + ae;
		result = prime * result + af;
		result = prime * result + ag;
		result = prime * result + ah;
		result = prime * result + ba;
		result = prime * result + bb;
		result = prime * result + bc;
		result = prime * result + bd;
		result = prime * result + be;
		result = prime * result + bf;
		result = prime * result + bg;
		result = prime * result + bh;
		result = prime * result + ca;
		result = prime * result + cb;
		result = prime * result + cc;
		result = prime * result + cd;
		result = prime * result + ce;
		result = prime * result + cf;
		result = prime * result + cg;
		result = prime * result + ch;
		result = prime * result + da;
		result = prime * result + db;
		result = prime * result + dc;
		result = prime * result + dd;
		result = prime * result + de;
		result = prime * result + df;
		result = prime * result + dg;
		result = prime * result + dh;
		result = prime * result + ea;
		result = prime * result + eb;
		result = prime * result + ec;
		result = prime * result + ed;
		result = prime * result + ee;
		result = prime * result + ef;
		result = prime * result + eg;
		result = prime * result + eh;
		result = prime * result + fa;
		result = prime * result + fb;
		result = prime * result + fc;
		result = prime * result + fd;
		result = prime * result + fe;
		result = prime * result + ff;
		result = prime * result + fg;
		result = prime * result + fh;
		result = prime * result + ga;
		result = prime * result + gb;
		result = prime * result + gc;
		result = prime * result + gd;
		result = prime * result + ge;
		result = prime * result + gf;
		result = prime * result + gg;
		result = prime * result + gh;
		result = prime * result + ha;
		result = prime * result + hb;
		result = prime * result + hc;
		result = prime * result + hd;
		result = prime * result + he;
		result = prime * result + hf;
		result = prime * result + hg;
		result = prime * result + hh;
		result = prime * result + id;
		result = prime * result + ((messages == null) ? 0 : messages.hashCode());
		result = prime * result + ((moves == null) ? 0 : moves.hashCode());
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		result = prime * result + ((whoseTurn == null) ? 0 : whoseTurn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (aa != other.aa)
			return false;
		if (ab != other.ab)
			return false;
		if (ac != other.ac)
			return false;
		if (ad != other.ad)
			return false;
		if (ae != other.ae)
			return false;
		if (af != other.af)
			return false;
		if (ag != other.ag)
			return false;
		if (ah != other.ah)
			return false;
		if (ba != other.ba)
			return false;
		if (bb != other.bb)
			return false;
		if (bc != other.bc)
			return false;
		if (bd != other.bd)
			return false;
		if (be != other.be)
			return false;
		if (bf != other.bf)
			return false;
		if (bg != other.bg)
			return false;
		if (bh != other.bh)
			return false;
		if (ca != other.ca)
			return false;
		if (cb != other.cb)
			return false;
		if (cc != other.cc)
			return false;
		if (cd != other.cd)
			return false;
		if (ce != other.ce)
			return false;
		if (cf != other.cf)
			return false;
		if (cg != other.cg)
			return false;
		if (ch != other.ch)
			return false;
		if (da != other.da)
			return false;
		if (db != other.db)
			return false;
		if (dc != other.dc)
			return false;
		if (dd != other.dd)
			return false;
		if (de != other.de)
			return false;
		if (df != other.df)
			return false;
		if (dg != other.dg)
			return false;
		if (dh != other.dh)
			return false;
		if (ea != other.ea)
			return false;
		if (eb != other.eb)
			return false;
		if (ec != other.ec)
			return false;
		if (ed != other.ed)
			return false;
		if (ee != other.ee)
			return false;
		if (ef != other.ef)
			return false;
		if (eg != other.eg)
			return false;
		if (eh != other.eh)
			return false;
		if (fa != other.fa)
			return false;
		if (fb != other.fb)
			return false;
		if (fc != other.fc)
			return false;
		if (fd != other.fd)
			return false;
		if (fe != other.fe)
			return false;
		if (ff != other.ff)
			return false;
		if (fg != other.fg)
			return false;
		if (fh != other.fh)
			return false;
		if (ga != other.ga)
			return false;
		if (gb != other.gb)
			return false;
		if (gc != other.gc)
			return false;
		if (gd != other.gd)
			return false;
		if (ge != other.ge)
			return false;
		if (gf != other.gf)
			return false;
		if (gg != other.gg)
			return false;
		if (gh != other.gh)
			return false;
		if (ha != other.ha)
			return false;
		if (hb != other.hb)
			return false;
		if (hc != other.hc)
			return false;
		if (hd != other.hd)
			return false;
		if (he != other.he)
			return false;
		if (hf != other.hf)
			return false;
		if (hg != other.hg)
			return false;
		if (hh != other.hh)
			return false;
		if (id != other.id)
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		if (moves == null) {
			if (other.moves != null)
				return false;
		} else if (!moves.equals(other.moves))
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		if (whoseTurn == null) {
			if (other.whoseTurn != null)
				return false;
		} else if (!whoseTurn.equals(other.whoseTurn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", moves=" + moves + ", players=" + players + ", messages=" + messages
				+ ", whoseTurn=" + whoseTurn + ", aa=" + aa + ", ab=" + ab + ", ac=" + ac + ", ad=" + ad + ", ae=" + ae
				+ ", af=" + af + ", ag=" + ag + ", ah=" + ah + ", ba=" + ba + ", bb=" + bb + ", bc=" + bc + ", bd=" + bd
				+ ", be=" + be + ", bf=" + bf + ", bg=" + bg + ", bh=" + bh + ", ca=" + ca + ", cb=" + cb + ", cc=" + cc
				+ ", cd=" + cd + ", ce=" + ce + ", cf=" + cf + ", cg=" + cg + ", ch=" + ch + ", da=" + da + ", db=" + db
				+ ", dc=" + dc + ", dd=" + dd + ", de=" + de + ", df=" + df + ", dg=" + dg + ", dh=" + dh + ", ea=" + ea
				+ ", eb=" + eb + ", ec=" + ec + ", ed=" + ed + ", ee=" + ee + ", ef=" + ef + ", eg=" + eg + ", eh=" + eh
				+ ", fa=" + fa + ", fb=" + fb + ", fc=" + fc + ", fd=" + fd + ", fe=" + fe + ", ff=" + ff + ", fg=" + fg
				+ ", fh=" + fh + ", ga=" + ga + ", gb=" + gb + ", gc=" + gc + ", gd=" + gd + ", ge=" + ge + ", gf=" + gf
				+ ", gg=" + gg + ", gh=" + gh + ", ha=" + ha + ", hb=" + hb + ", hc=" + hc + ", hd=" + hd + ", he=" + he
				+ ", hf=" + hf + ", hg=" + hg + ", hh=" + hh + "]";
	}
}