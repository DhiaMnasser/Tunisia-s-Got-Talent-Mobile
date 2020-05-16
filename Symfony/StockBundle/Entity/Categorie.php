<?php

namespace StockBundle\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\ORM\Mapping\ManyToOne;
use Doctrine\ORM\Mapping\OneToMany;
use Doctrine\ORM\Mapping\JoinColumn;

/**
 * Categorie
 *
 * @ORM\Table(name="Categorie")
 * @ORM\Entity(repositoryClass="StockBundle\Repository\CategorieRepository")
 */
class Categorie
{ /**
 * @var int
 *
 * @ORM\Column(name="id", type="integer")
 * @ORM\Id
 * @ORM\GeneratedValue(strategy="AUTO")
 */
    private $id;

    /**
     * @OneToMany(targetEntity="produit",mappedBy="categorie")
     */
    private $categorie;

    /**
     * @param mixed $categorie
     */
    public function setCategorie($categorie)
    {
        $this->categorie = $categorie;
    }

    /**
     * @return mixed
     */
    public function _construct()
    {
        $this->produits=new ArrayCollection();
    }

    /**
     * @var string
     *
     * @ORM\Column(name="nomc", type="string", length=255)
     */
    private $nomc;


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
     * Set nomc
     *
     * @param string $nomc
     *
     * @return categorie
     */
    public function setNomc($nomc)
    {
        $this->nomc = $nomc;

        return $this;
    }

    /**
     * Get nomc
     *
     * @return string
     */
    public function getNomc()
    {
        return $this->nomc;
    }

    /**
     * @return mixed
     */
    public function getCategorie()
    {
        return $this->categorie;
    }

    public function __toString()
    {
        return $this->nomc;
    }
}

