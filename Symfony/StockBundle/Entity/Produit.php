<?php

namespace StockBundle\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\ORM\Mapping\ManyToOne;
use Doctrine\ORM\Mapping\OneToMany;
use Doctrine\ORM\Mapping\JoinColumn;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * Produit
 *
 * @ORM\Table(name="Produit")
 * @ORM\Entity(repositoryClass="StockBundle\Repository\ProduitRepository")
 */
//class Produit
//{
//    /**
//     * @var int
//     *
//     * @ORM\Column(name="id", type="integer")
//     * @ORM\Id
//     * @ORM\GeneratedValue(strategy="AUTO")
//     */
//    private $id;
//
//    /**
//     * @ManyToOne(targetEntity="Categorie",inversedBy="Produit")
//     * @JoinColumn(name="cat_id",referencedColumnName="id")
//     */
//     private $cat;
//
//    /**
//     * @return mixed
//     */
//    public function getCat()
//    {
//        return $this->cat;
//    }
//
//    /**
//     * @param mixed $cat
//     */
//    public function setCat($cat)
//    {
//        $this->cat = $cat;
//    }
////
////    /**
////     * @param mixed $panier
////     */
////    public function setPanier($panier)
////    {
////        $this->panier = $panier;
////    }
//
//
////    /**
////     * @return mixed
////     */
////    public function getPanier()
////    {
////        return $this->panier;
////    }
//
//
//    /**
//     * @return mixed
//     */
//    public function _construct()
//    {
//        $this->panniers=new ArrayCollection();
//    }
//    /**
//     * @var string
//     *
//     * @ORM\Column(name="nom", type="string", length=255)
//     */
//    private $nom;
//
//    /**
//     * @var int
//     *
//     * @ORM\Column(name="qte", type="integer")
//     */
//    private $qte;
//
//    /**
//     * @var float
//     *
//     * @ORM\Column(name="prix", type="float")
//     */
//    private $prix;
//
//    /**
//     * @var string
//     *
//     * @ORM\Column(name="etat", type="string", length=255)
//     */
//    private $etat;
//
//    /**
//     * @var string
//     *
//     * @ORM\Column(name="size", type="string", length=255)
//     */
//    private $size;
//
//
//
//    /**
//     * @var string
//     *
//     * @ORM\Column(name="url", type="string", length=255)
//     */
//    private $url;
//
//    /**
//     * @return string
//     */
//    public function getUrl()
//    {
//        return $this->url;
//    }
//
//    /**
//     * @param string $url
//     */
//    public function setUrl($url)
//    {
//        $this->url = $url;
//    }
//
//
//
//    /**
//     * Get id
//     *
//     * @return int
//     */
//    public function getId()
//    {
//        return $this->id;
//    }
//
//    /**
//     * Set nom
//     *
//     * @param string $nom
//     *
//     * @return produit
//     */
//    public function setNom($nom)
//    {
//        $this->nom = $nom;
//
//        return $this;
//    }
//
//    /**
//     * Get nom
//     *
//     * @return string
//     */
//    public function getNom()
//    {
//        return $this->nom;
//    }
//
//    /**
//     * Set qte
//     *
//     * @param integer $qte
//     *
//     * @return produit
//     */
//    public function setQte($qte)
//    {
//        $this->qte = $qte;
//
//        return $this;
//    }
//
//    /**
//     * Get qte
//     *
//     * @return int
//     */
//    public function getQte()
//    {
//        return $this->qte;
//    }
//
//    /**
//     * Set prix
//     *
//     * @param float $prix
//     *
//     * @return produit
//     */
//    public function setPrix($prix)
//    {
//        $this->prix = $prix;
//
//        return $this;
//    }
//
//    /**
//     * Get prix
//     *
//     * @return float
//     */
//    public function getPrix()
//    {
//        return $this->prix;
//    }
//
//    /**
//     * Set etat
//     *
//     * @param string $etat
//     *
//     * @return produit
//     */
//    public function setEtat($etat)
//    {
//        $this->etat = $etat;
//
//        return $this;
//    }
//
//    /**
//     * Get etat
//     *
//     * @return string
//     */
//    public function getEtat()
//    {
//        return $this->etat;
//    }
//
//    /**
//     * Set size
//     *
//     * @param string $size
//     *
//     * @return produit
//     */
//    public function setSize($size)
//    {
//        $this->size = $size;
//
//        return $this;
//    }
//
//    /**
//     * Get size
//     *
//     * @return string
//     */
//    public function getSize()
//    {
//        return $this->size;
//    }
//
//    public function __toString()
//    {
//        return $this->nom;
//    }
//}


class Produit{

    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @ManyToOne(targetEntity="Categorie",inversedBy="Produit")
     * @JoinColumn(name="cat_id",referencedColumnName="id")
     */
    private $categorie;

    /**
     * @return mixed
     */
    public function getCat()
    {
        return $this->categorie;
    }

    /**
     * @param mixed $cat
     */
    public function setCat($cat)
    {
        $this->categorie = $cat;
    }

    /**
     * @param mixed $panier
     */
    public function setPanier($panier)
    {
        $this->panier = $panier;
    }



    /**
     * @return mixed
     */
    public function _construct()
    {
        $this->panniers=new ArrayCollection();
    }
    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $nom;

    /**
     * @var int
     *
     * @ORM\Column(name="qte", type="integer")
     */
    private $qte;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float")
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=255)
     */
    private $etat;

    /**
     * @var string
     *
     * @ORM\Column(name="size", type="string", length=255)
     */
    private $size;



    /**
     * @var string
     *
     * @ORM\Column(name="url", type="string", length=255)
     */
    private $url;

    /**
     * @return string
     */
    public function getUrl()
    {
        return $this->url;
    }

    /**
     * @param string $url
     */
    public function setUrl($url)
    {
        $this->url = $url;
    }



    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return produit
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set qte
     *
     * @param integer $qte
     *
     * @return produit
     */
    public function setQte($qte)
    {
        $this->qte = $qte;

        return $this;
    }

    /**
     * Get qte
     *
     * @return int
     */
    public function getQte()
    {
        return $this->qte;
    }

    /**
     * Set prix
     *
     * @param float $prix
     *
     * @return produit
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;

        return $this;
    }

    /**
     * Get prix
     *
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * Set etat
     *
     * @param string $etat
     *
     * @return produit
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;

        return $this;
    }

    /**
     * Get etat
     *
     * @return string
     */
    public function getEtat()
    {
        return $this->etat;
    }

    /**
     * Set size
     *
     * @param string $size
     *
     * @return produit
     */
    public function setSize($size)
    {
        $this->size = $size;

        return $this;
    }

    /**
     * Get size
     *
     * @return string
     */
    public function getSize()
    {
        return $this->size;
    }

    public function __toString()
    {
        return $this->nom;
    }

}
