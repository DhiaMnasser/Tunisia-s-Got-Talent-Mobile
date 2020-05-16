<?php

namespace AchatBundle\Entity;

use Doctrine\ORM\Mapping as ORM;


/**
 * LigneCommande
 *
 * @ORM\Table(name="ligne_commande")
 * @ORM\Entity(repositoryClass="AchatBundle\Repository\LigneCommandeRepository")
 */
class LigneCommande
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity="AchatBundle\Entity\Panier")
     * @ORM\JoinColumn(name="idPanier", referencedColumnName="id")
     */

    private $idPanier;

    /**
     * @ORM\ManyToOne(targetEntity="StockBundle\Entity\Produit")
     * @ORM\JoinColumn(name="idproduit", referencedColumnName="id")
     */

    private $idproduit;

    /**
     * @var string
     *
     * @ORM\Column(name="nomProduit", type="string", length=20, nullable=true)
     */
    private $nomproduit;

    /**
     * @var integer
     *
     * @ORM\Column(name="quantite", type="integer", nullable=true)
     */
    private $quantite;







    /**
     * Get id.
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @return \AchatBundle\entity\Panier
     */
    public function getIdPanier()
    {
        return $this->idPanier;
    }

    /**
     *
     * @param mixed $idPanier
     * @return LigneCommande
     */
    public function setIdPanier(\AchatBundle\entity\Panier $idPanier)
    {
        $this->idPanier = $idPanier;
        return $this;
    }

    /**
     * @return \StockBundle\Entity\Produit
     */
    public function getIdproduit()
    {
        return $this->idproduit;
    }

    /**
     * @param mixed $idproduit
     * @return LigneCommande
     */
    public function setIdproduit(\StockBundle\Entity\Produit $idproduit)
    {
        $this->idproduit = $idproduit;
        return $this;
    }

    /**
     * @return string
     */
    public function getNomproduit()
    {
        return $this->nomproduit;
    }

    /**
     * @param string $nomproduit
     */
    public function setNomproduit($nomproduit)
    {
        $this->nomproduit = $nomproduit;
    }

    /**
     * @return int
     */
    public function getQuantite()
    {
        return $this->quantite;
    }

    /**
     * @param int $quantite
     */
    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;
    }



}