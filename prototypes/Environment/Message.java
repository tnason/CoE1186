package TLTTC;
import java.util.*;

/*



*/

public class Message implements constData
{
	private Hashtable<String, Object> data;
	private Module sender, source, dest;
	private msg mType;

	public Message(Module sender, Module source, Module dest, msg mType)
	{
		this.sender = sender;
		this.source = source;
		this.dest   = dest;
		this.mType 	= mType;

		data = new Hashtable<String, Object>();
	}

	public Message(Module sender, Module source, Module dest, msg mType,
					String[] keys, Object[] values)
	{
		this(sender, source, dest, mType);

		if(keys != null && values != null && keys.length == values.length)
		{
			for(int i = 0; i < keys.length; i++)
			{
				data.put(keys[i], values[i]);
			}
		}
	}

	public Message(Module sender, Message m)
	{
		data = new Hashtable<String, Object>();

		if(sender != null && m != null)
		{
			this.sender = sender;
			this.source = m.getSource();
			this.dest   = m.getDest();

			Hashtable<String, Object> copy = m.getData();

			if(copy != null)
			{
				data.putAll(copy);
			}
		}
	}

	public boolean addData(String key, Object value)
	{
		if(key != null && value != null)
		{
			data.put(key, value);

			return true;
		}

		return false;
	}

	public boolean updateSender(Module sender)
	{
		if(sender != null)
		{
			this.sender = sender;
			return true;
		}

		return false;
	}

	public Module getSender()
	{
		return sender;
	}

	public Module getSource()
	{
		return source;
	}

	public Module getDest()
	{
		return dest;
	}
	
	public msg getType()
	{
		return mType;
	}

	public Hashtable<String, Object>  getData()
	{
		return data;
	}
}
