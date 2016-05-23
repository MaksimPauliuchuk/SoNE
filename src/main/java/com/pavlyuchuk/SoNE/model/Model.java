package com.pavlyuchuk.SoNE.model;

/**
 * @author Maksim Pauliuchuk
 */
public class Model
{
	private String mu1_Text;
	private String mu2_Text;
	private String mu3_Text;
	private String K_Text;
	private String Ku_Text;
	private String g_Text;
	private String xFrom_Text;
	private String xTo_Text;
	private String tFrom_Text;
	private String tTo_Text;
	private String N_Text;
	private String M_Text;
	private String eSystem_Text;
	private String eRunge_Text;
	private String exactSolution_Text;
	private String useRunge_Text;

	public Model(String mu1_Text, String mu2_Text, String mu3_Text, String k_Text, String ku_Text, String g_Text,
			String xFrom_Text, String xTo_Text, String tFrom_Text, String tTo_Text, String n_Text, String m_Text,
			String eSystem_Text, String eRunge_Text, String exactSolution_Text, String useRunge_Text)
	{
		this.mu1_Text = mu1_Text;
		this.mu2_Text = mu2_Text;
		this.mu3_Text = mu3_Text;
		K_Text = k_Text;
		Ku_Text = ku_Text;
		this.g_Text = g_Text;
		this.xFrom_Text = xFrom_Text;
		this.xTo_Text = xTo_Text;
		this.tFrom_Text = tFrom_Text;
		this.tTo_Text = tTo_Text;
		N_Text = n_Text;
		M_Text = m_Text;
		this.eSystem_Text = eSystem_Text;
		this.eRunge_Text = eRunge_Text;
		this.exactSolution_Text = exactSolution_Text;
		this.useRunge_Text = useRunge_Text;
	}

	public final String getMu1_Text()
	{
		return mu1_Text;
	}

	public final void setMu1_Text(String mu1_Text)
	{
		this.mu1_Text = mu1_Text;
	}

	public final String getMu2_Text()
	{
		return mu2_Text;
	}

	public final void setMu2_Text(String mu2_Text)
	{
		this.mu2_Text = mu2_Text;
	}

	public final String getMu3_Text()
	{
		return mu3_Text;
	}

	public final void setMu3_Text(String mu3_Text)
	{
		this.mu3_Text = mu3_Text;
	}

	public final String getK_Text()
	{
		return K_Text;
	}

	public final void setK_Text(String k_Text)
	{
		K_Text = k_Text;
	}

	public final String getKu_Text()
	{
		return Ku_Text;
	}

	public final void setKu_Text(String ku_Text)
	{
		Ku_Text = ku_Text;
	}

	public final String getG_Text()
	{
		return g_Text;
	}

	public final void setG_Text(String g_Text)
	{
		this.g_Text = g_Text;
	}

	public final String getxFrom_Text()
	{
		return xFrom_Text;
	}

	public final void setxFrom_Text(String xFrom_Text)
	{
		this.xFrom_Text = xFrom_Text;
	}

	public final String getxTo_Text()
	{
		return xTo_Text;
	}

	public final void setxTo_Text(String xTo_Text)
	{
		this.xTo_Text = xTo_Text;
	}

	public final String gettFrom_Text()
	{
		return tFrom_Text;
	}

	public final void settFrom_Text(String tFrom_Text)
	{
		this.tFrom_Text = tFrom_Text;
	}

	public final String gettTo_Text()
	{
		return tTo_Text;
	}

	public final void settTo_Text(String tTo_Text)
	{
		this.tTo_Text = tTo_Text;
	}

	public final String getN_Text()
	{
		return N_Text;
	}

	public final void setN_Text(String n_Text)
	{
		N_Text = n_Text;
	}

	public final String getM_Text()
	{
		return M_Text;
	}

	public final void setM_Text(String m_Text)
	{
		M_Text = m_Text;
	}

	public final String geteSystem_Text()
	{
		return eSystem_Text;
	}

	public final void seteSystem_Text(String eSystem_Text)
	{
		this.eSystem_Text = eSystem_Text;
	}

	public final String geteRunge_Text()
	{
		return eRunge_Text;
	}

	public final void seteRunge_Text(String eRunge_Text)
	{
		this.eRunge_Text = eRunge_Text;
	}

	public final String getExactSolution_Text()
	{
		return exactSolution_Text;
	}

	public final void setExactSolution_Text(String exactSolution_Text)
	{
		this.exactSolution_Text = exactSolution_Text;
	}

	public final String getUseRunge_Text()
	{
		return useRunge_Text;
	}

	public final void setUseRunge_Text(String useRunge_Text)
	{
		this.useRunge_Text = useRunge_Text;
	}
}
