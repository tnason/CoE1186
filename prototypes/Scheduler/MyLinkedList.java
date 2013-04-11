package TLTTC;

public class MyLinkedList<T extends Comparable<? super T>>
{
	private Node head;
	private Node selected;

	private int size;

	public MyLinkedList()
	{
		head = null;
		selected = null;
		size = 0;
	}

	public MyLinkedList(MyLinkedList list)
	{
		if(list != null)
		{
			head = list.head();
			selected = head;
			size = list.size();
		}
	}
	public boolean add(T o)
	{
		if(head == null)
		{
			head = new Node(o);
			head.previous = head;
			head.next = head;
			selected = head;
			size = 1;

			return true;
		}
		else
		{
			Node node;

			node = head;

			do
			{
				if(node.data.equals(o))
				{
					return false;
				}

				if(node.data.compareTo(o) < 0 && node.next.data.compareTo(o) > 0)
				{
					Node temp;

					temp = new Node(o);
					temp.previous = node;
					temp.next = node.next;
					temp.previous.next = temp;
					temp.next.previous = temp;
					size++;

					return true;
				}

				node = node.next;
			}
			while(!head.equals(node));

			node = head.previous;
			node.next = new Node(o);
			node.next.previous = node;
			node.next.next = head;
			head.previous = node.next;
			size++;

			return true;
		}
	}

	public boolean remove(T o)
	{
		Node node;

		if(head == null)
		{
			return false;
		}

		node = head;

		do
		{
			if(node.data.equals(o))
			{
				Node temp;

				if(node.equals(head))
				{
					if(size == 1)
					{
						head = null;
						selected = null;
						size = 0;

						return true;
					}
					else
					{
						head = head.next;
					}
				}

				if(node.equals(selected))
				{
					selected = node.next;
				}

				temp = node.previous;
				temp.next = node.next;
				temp.next.previous = temp;
				node = temp;
				size--;

				return true;
			}

			node = node.next;
		}
		while(!head.equals(node));

		return false;
	}

	public T head()
	{
		return head.data;
	}

	public T selected()
	{
		return selected.data;
	}

	public T next()
	{
		if(size == 0)
		{
			return null;
		}

		selected = selected.next;

		return selected.previous.data;
	}

	public T previous()
	{
		if(size == 0)
		{
			return null;
		}

		selected = selected.previous;

		return selected.next.data;
	}

	public void reset()
	{
		selected = head;
	}

	public int size()
	{
		return size;
	}

	public String toString()
	{
		String s;
		Node node;

		if(size == 0)
		{
			return null;
		}

		node = head;
		s = new String(node.data.toString());
		node = node.next;

		while(!node.equals(head))
		{
			s = s.concat("\n" + node.data.toString());
			node = node.next;
		}

		return s;
	}
	private class Node
	{
		public Node previous;
		public Node next;
		public T data;

		public Node(T data)
		{
			this.data = data;
		}
	}
}