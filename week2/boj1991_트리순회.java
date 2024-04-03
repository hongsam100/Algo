//트리 순회
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class node1991 {
	node1991 left;
	node1991 right;
	String lefta;
	String righta;
	String me;

	node1991(String me, String lefta, String righta) {
		this.me = me;
		this.lefta = lefta;
		this.righta = righta;
	}
}

public class boj1991_트리순회 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		node1991[] nodes = new node1991[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[i] = new node1991(st.nextToken(), st.nextToken(), st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (nodes[i].lefta.equals(nodes[j].me)) {
					nodes[i].left = nodes[j];
				}
				if (nodes[i].righta.equals(nodes[j].me)) {
					nodes[i].right = nodes[j];
				}
			}
		}
		
		preorder(nodes[0]);
		System.out.println();
		inorder(nodes[0]);
		System.out.println();
		postorder(nodes[0]);
	}

	static void preorder(node1991 node) {
		if (node == null) {
			return;
		}
		System.out.print(node.me);
		preorder(node.left);
		preorder(node.right);
	}

	static void inorder(node1991 node) {
		if (node == null) {
			return;
		}
		inorder(node.left);
		System.out.print(node.me);
		inorder(node.right);

	}

	static void postorder(node1991 node) {
		if (node == null) {
			return;
		}
		postorder(node.left);
		postorder(node.right);
		System.out.print(node.me);
	}

}