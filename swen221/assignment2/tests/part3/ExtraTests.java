package swen221.assignment2.tests.part3;

import static swen221.assignment2.tests.TestHelpers.*;

import org.junit.Test;
import junit.framework.TestCase;

/**
 * This class borrows the tests uploaded by Hector Zhao from the facebook.
 * @author yanlong
 *
 */
public class ExtraTests {
	public @Test void testValidChecks() {
		String[][] tests = {
				// Test 1
				{"e2-e3 f7-f5\nBf1-d3 g7-g6\nBd3xf5 e7-e6\nBf5xg6+ Ke8-e7\nBg6xh7 d7-d5",
					"8|r|n|b|q|_|b|n|r|\n"+
							"7|p|p|p|_|k|_|_|B|\n"+
							"6|_|_|_|_|p|_|_|_|\n"+
							"5|_|_|_|p|_|_|_|_|\n"+
							"4|_|_|_|_|_|_|_|_|\n"+
							"3|_|_|_|_|P|_|_|_|\n"+
							"2|P|P|P|P|_|P|P|P|\n"+
							"1|R|N|B|Q|K|_|N|R|\n"+
							"  a b c d e f g h"
				},
				{"c2-c4 e7-e5\nc4-c5 Bf8xc5\nd2-d4 Bc5xd4\ne2-e3 Bd4xe3\nh2-h4 Be3xf2+\nKe1xBf2",
					"8|r|n|b|q|k|_|n|r|\n"+
							"7|p|p|p|p|_|p|p|p|\n"+
							"6|_|_|_|_|_|_|_|_|\n"+
							"5|_|_|_|_|p|_|_|_|\n"+
							"4|_|_|_|_|_|_|_|P|\n"+
							"3|_|_|_|_|_|_|_|_|\n"+
							"2|P|P|_|_|_|K|P|_|\n"+
							"1|R|N|B|Q|_|B|N|R|\n"+
							"  a b c d e f g h"
				},
				{"d2-d3 d7-d6\nBc1-e3 Bc8-e6\nBe3-c5 Be6-c4\nBc5-b4 Bc4-b5\nBb4-d2 Bb5-d7",
					"8|r|n|_|q|k|b|n|r|\n"+
							"7|p|p|p|b|p|p|p|p|\n"+
							"6|_|_|_|p|_|_|_|_|\n"+
							"5|_|_|_|_|_|_|_|_|\n"+
							"4|_|_|_|_|_|_|_|_|\n"+
							"3|_|_|_|P|_|_|_|_|\n"+
							"2|P|P|P|B|P|P|P|P|\n"+
							"1|R|N|_|Q|K|B|N|R|\n"+
							"  a b c d e f g h"
				},
				{"d2-d4 d7-d5\nc2-c4 e7-e6\nNg1-f3 Ng8-f6\nNb1-c3 Bf8-e7\nBc1-g5 h7-h6\n" +
						"Bg5-h4 O-O\ne2-e3 Nf6-e4\nBh4xBe7 Qd8xBe7\nRa1-c1 c7-c6\nBf1-e2 Ne4xNc3\n" +
						"Rc1xNc3 d5xc4\nBe2xc4 Nb8-d7\nO-O b7-b6\nBc4-d3 c6-c5\nBd3-e4 Ra8-b8\n" +
						"Qd1-c2 Nd7-f6\nd4xc5 Nf6xBe4\nQc2xNe4 b6xc5\nQe4-c2 Bc8-b7\nNf3-d2 Rf8-d8\n" +
						"f2-f3 Bb7-a6\nRf1-f2 Rd8-d7\ng2-g3 Rb8-d8\nKg1-g2 Ba6-d3\nQc2-c1 Bd3-a6\n" +
						"Rc3-a3 Ba6-b7\nNd2-b3 Rd7-c7\nNb3-a5 Bb7-a8\nNa5-c4 e6-e5\ne3-e4 f7-f5\n" +
						"e4xf5 e5-e4\nf3xe4 Qe7xe4+\nKg2-h3 Rd8-d4\nNc4-e3 Qe4-e8\ng3-g4 h6-h5\n" +
						"Kh3-h4 g7-g5+\nf5xg6ep Qe8xg6\nQc1-f1 Rd4xg4+\nKh4-h3 Rc7-e7\nRf2-f8+ Kg8-g7\n" +
						"Ne3-f5+ Kg7-h7\nRa3-g3 Rg4xRg3+\nh2xRg3 Qg6-g4+\nKh3-h2 Re7-e2+\nKh2-g1 Re2-g2+\n" +
						"Qf1xRg2 Ba8xQg2\nKg1xBg2 Qg4-e2+\nKg2-h3 c5-c4\na2-a4 a7-a5\nRf8-f6 Kh7-g8\n" +
						"Nf5-h6+ Kg8-g7\nRf6-b6 Qe2-e4\nKh3-h2 Kg7-h7\nRb6-d6 Qe4-e5\nNh6-f7 Qe5xb2+\n" +
						"Kh2-h3 Qb2-g7",
						"8|_|_|_|_|_|_|_|_|\n"+
								"7|_|_|_|_|_|N|q|k|\n"+
								"6|_|_|_|R|_|_|_|_|\n"+
								"5|p|_|_|_|_|_|_|p|\n"+
								"4|P|_|p|_|_|_|_|_|\n"+
								"3|_|_|_|_|_|_|P|K|\n"+
								"2|_|_|_|_|_|_|_|_|\n"+
								"1|_|_|_|_|_|_|_|_|\n"+
								"  a b c d e f g h"
				},
				{"e2-e3 f7-f5\nBf1-d3 g7-g6\nBd3xf5 e7-e6\nBf5xg6+ Ke8-e7\nBg6xh7 d7-d5",
					"8|r|n|b|q|_|b|n|r|\n"+
							"7|p|p|p|_|k|_|_|B|\n"+
							"6|_|_|_|_|p|_|_|_|\n"+
							"5|_|_|_|p|_|_|_|_|\n"+
							"4|_|_|_|_|_|_|_|_|\n"+
							"3|_|_|_|_|P|_|_|_|\n"+
							"2|P|P|P|P|_|P|P|P|\n"+
							"1|R|N|B|Q|K|_|N|R|\n"+
							"  a b c d e f g h"
				},
				{"c2-c4 e7-e5\nc4-c5 Bf8xc5\nd2-d4 Bc5xd4\ne2-e3 Bd4xe3\nh2-h4 Be3xf2+\nKe1xBf2",
					"8|r|n|b|q|k|_|n|r|\n"+
							"7|p|p|p|p|_|p|p|p|\n"+
							"6|_|_|_|_|_|_|_|_|\n"+
							"5|_|_|_|_|p|_|_|_|\n"+
							"4|_|_|_|_|_|_|_|P|\n"+
							"3|_|_|_|_|_|_|_|_|\n"+
							"2|P|P|_|_|_|K|P|_|\n"+
							"1|R|N|B|Q|_|B|N|R|\n"+
							"  a b c d e f g h"
				},
				{"f2-f4 e7-e5\nKe1-f2 c7-c6\nKf2-e3 g7-g6\nKe3-e4 h7-h6\nKe4xe5 f7-f6+\nKe5xf6",
					// the last move is not executed.
					// king should not be able to take as its in check by the queen
					"8|r|n|b|q|k|b|n|r|\n"+
					"7|p|p|_|p|_|_|_|_|\n"+
					"6|_|_|p|_|_|p|p|p|\n"+
					"5|_|_|_|_|K|_|_|_|\n"+
					"4|_|_|_|_|_|P|_|_|\n"+
					"3|_|_|_|_|_|_|_|_|\n"+
					"2|P|P|P|P|P|_|P|P|\n"+
					"1|R|N|B|Q|_|B|N|R|\n"+
					"  a b c d e f g h"
				},
				{"e2-e4 d7-d5\nKe1-e2 Nb8-c6\ne4xd5 Nc6-d4+\nKe2-d3 e7-e5\nd5xe6ep Qd8-e7\nKd3xNd4",
					"8|r|_|b|_|k|b|n|r|\n"+
							"7|p|p|p|_|q|p|p|p|\n"+
							"6|_|_|_|_|P|_|_|_|\n"+
							"5|_|_|_|_|_|_|_|_|\n"+
							"4|_|_|_|K|_|_|_|_|\n"+
							"3|_|_|_|_|_|_|_|_|\n"+
							"2|P|P|P|P|_|P|P|P|\n"+
							"1|R|N|B|Q|_|B|N|R|\n"+
							"  a b c d e f g h"
				}


		};
		checkValidTests(tests);
	}

	public @Test void testErrors(){
		String[] tests = {
				//"d2-d4 e7-e6\nd4-d5 c7-c5\nd5xc6ep Nb8-a6\nc6xd7+ Ke8-e7\nd7xBc8"
				"d2-d4 d7-d5\ne2-e4 Qd8-d6\nc2-c3 e7-e5\nd4xe5 Qd6xe5\nc3-c4 Bc8-e6\nKe1-d2 d5xc4\na2-a3 Nb8-c6\na3-a4 O-O-O+"
		};
		checkInvalidTests(tests);
	}

	public @Test void testInvalidChecks() {
		String[] tests = {
				"c2-c3 e7-e6\nd2-d4 Bf8-c5\nBc1-d2 Bc5xa7",
				"c2-c4 e7-e6\nd2-d4 Bf8-b4+\nBc1-d2 Bb4xe1",
				"c2-c4 e7-e6\nd2-d4 Bf8-b4+\nBc1-d2 Bb4xd2+",
				"d2-d4 e7-e6\nKe1-d3",
				"e2-e4 e7-e6\nKe1-e2 e6-e5\nKe2xe5",
				"e2-e4 e7-e6\nKe1-e2 e6-e5\nKe2-f1",
				"f2-f4 e7-e5\nKe1-f2 c7-c6\nKf2-e3 g7-g6\nKe3-e4 h7-h6\nKe4xe5 f7-f6+\nKe5xf6",
				"e2-e4 Nb8-c6\ne4-e5 Nc6xc5",
				"d2-d4 Nb8-c6\nNg1-f3 Nc6-d4",
				"d2-d4 Nb8-c6\nNg1-f3 Nc6xNd4",
				"d2-d4 Nb8-c6\nNg1-f3 Nc6xd4\nNf3xKe4+",
				"d2-d4 Nb8-c6\nNg1-f3 Nc6xd4\nNf3xNe4+",
				"d2-d4 Nb8-c6\nNg1-f3 Nc6xd4\nNf3xNd4 a7-a5\nNd4-f5 b7-b5\nNf5-d6",
				"d2-d4 Nb8-c6\nNg1-f3 Nc6xd4\nKe1-d2 Nd4xNf3+\nKd2-e3 Nf3-e1\nKe3-d2 Ne1xNb1+",
				"e2-e4 e7-e5\ne4-e6",
				"e2-e4 e7-e5\ne4-d3",
				"a2-a3 d7-d5\na3-a4 d5-d4\na4-a5 d4-d3\nd2xd3",
				"a2-a3 d7-d5\na3-a4 d5-d4\nd2xd4",
				"a2-a3 d7-d5\na3-a4 d5-d4\nc2xd4",
				"c2-c4 e7-e6\nc4-c5 d7-d6\nc5xe6",
				"c2-c4 d7-d5\nc4-c5 d5-d4\nd2-d3 d4xd3",
				"c2-c4 d7-d5\nc4-c5 d5-d4\ne2-e3 d4xe3\nBf1-e2 e3xf2",
				"c2-c4 d7-d5\nc4-c5 d5-d4\ne2-e3 d4xe3\nBf1-e2 e3xf2+\nKe1-f1 f2xf1",
				"e2-e4 d7-d5\nQd1-e2 d5xe4\nQe2-e4",
				"e2-e4 d7-d5\nQd1-e2 d5xe4\nQe2xf1+",
				"c2-c3 d7-d5\nQd1-b3 Qd8-d7\nQb3xQd7+",
				"c2-c3 d7-d5\nQd1-b3 Qd8-d7\nQb3xd5+",
				"c2-c3 d7-d5\nQd1-b3 Qd8-d7\nQb3xd5 Qd7-a4\nQd5-b3 Bc8-e6\nKe1-d1 Be6-g4\nQb3-e4+",
				"a2-a3 d7-d5\nb2-b3 Qd8-d6\nc2-c3 Bc8-h3\nd2-d3 Nb8-c6\ne2-e3 Ke8-d8\nf2-f3 Kd8-e8\ng2-g3 O-O-O",
				"a2-a3 d7-d5\nb2-b3 Qd8-d6\nc2-c3 Bc8-h3\nd2-d3 Nb8-c6\ne2-e3 Ra8-d8\nf2-f3 Rd8-a8\ng2-g3 O-O-O",
				"a2-a3 d7-d5\nb2-b3 Qd8-d6\nc2-c3 Bc8-h3\nd2-d3 Nb8-c6\ne2-e3 Ra8-d8\nf2-f3 Rd8-a8\ng2-g3 Ke8-d8\na3-a4 Kd8-e8\nb3-b4 O-O-O",
				"Nb1-a3 a7-a6\nd2-d3 b7-b6\nBc1-h6 c7-c6\nc2-c3 d7-d6\nQd1-a4 e7-e6\nNa3-c4 f7-f6\na2-a3 g7-g6\nb2-b3 a6-a5\ne2-e3 b6-b5\nRa1-a2 c6-c5\nKe1-e2 d6-d5\nO-O-O",
				// whether the king can castle out of, through or into check

				"d2-d4 d7-d5\ne2-e4 e7-e5\na2-a3 c7-c5\nb2-b3 Qd8-h4\nc2-c3 Bc8-g4\ng2-g3 Nb8-a6\nBf1-b5+ O-O-O", // out of
				"d2-d4 d7-d5\ne2-e4 e7-e5\na2-a3 c7-c5\nb2-b3 Qd8-h4\nc2-c3 Bc8-g4\ng2-g3 Nb8-a6\nQd1xBg4 b7-b6\ng3xQh4 O-O-O",
				//en passant
				"a2-a3 b7-b5\na3-a4 b5-b4\na4xb5ep",
				"a2-a4 c7-c6\na4-a5 Qd8-b6\nh2-h3 Qb6-b5\na5xQb6ep",
				"a2-a4 c7-c6\na4-a5 b7-b6\na5xb6ep",
				"a2-a4 a7-a5\na4xb5ep",
				//tricky!
				"d2-d4 e7-e5\nd4-d5 h7-h6\nd5xe6ep",
				"a2-a3 e7-e5\nd2-d4 e5-e4\nb2-b3 e4xd3ep",
				"d2-d4 a7-a6\nd4-d5 e7-e5\na2-a3 b7-b6\nd5xe6ep",
				"a2-a3 d7-d5\nb2-b3 d5-d4\ne2-e4 a7-a6\nc2-c3 d4xe3ep",
				//extra
				"a2-a4 b7-b5\na4xb5 Nb8-c6\nb5-b6 a7-a5\nNb1-c3 Bc8-a6\nb6-b7 Qd8-c8\nb7xQc8=N+",
				"a2-a4 b7-b5\na4xb5 Nb8-c6\nb5-b6 a7-a5\nNb1-c3 Bc8-a6\nb6-b7 Qd8-c8\nb7-d8=R",
				"a2-a4 b7-b5\na4xb5 Nb8-c6\nb5-b6 a7-a5\nNb1-c3 Bc8-a6\nb6-b7 Qd8-c8\nb7xBd8=K+",
				// for black pawns
				"a2-a3 d7-d5\nb2-b3 d5-d4\nc2-c4 d4xc3ep\nBc1-b2 c3xBb2\nQd1-c1 b2xQc1=B+",
				"a2-a3 d7-d5\nb2-b3 d5-d4\nc2-c4 d4xc3ep\nBc1-b2 c3xBb2\nQd1-c1 b2xQc1=Q",
				// The pawn cannot remain as a pawn

				"a2-a3 d7-d5\nb2-b3 d5-d4\nc2-c4 d4xc3ep\nBc1-b2 c3xBb2\nQd1-c1 b2xQc1",
				"d2-d4 e7-e6\nd4-d5 c7-c5\nd5xc6ep Nb8-a6\nc6xd7+ Ke8-e7\nd7xBc8",

				"f2-f4 e7-e6\nNg1-h3 b7-b6\ne2-e4 Bf8-d6\nBf1-d3 a7-a6\nO-O a6-a5\nKg1-f2 a5-a4\nKf2-e1 a4-a3\nRf1-h1 a3xb2\nO-O",
				"Ng1-h3 Ng8-f6\nNh3-i5",
				"d2-d3 e7-e5\nBc1-f4 a7-a6\nBf4-i7",
				"d2-d4 d7-d5\ne2-e3 Qd8-d6\nc2-c3 Ke8-e9"
		};

		checkInvalidTests(tests);
	}
}
